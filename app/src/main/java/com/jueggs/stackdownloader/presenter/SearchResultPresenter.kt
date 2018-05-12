package com.jueggs.stackdownloader.presenter

import com.jueggs.domain.model.SearchCriteria
import com.jueggs.stackdownloader.*
import com.jueggs.stackdownloader.bo.*
import com.jueggs.data.DataProvider
import com.jueggs.stackdownloader.data.entity.DaoSession
import com.jueggs.stackdownloader.factory.RendererFactory
import com.jueggs.stackdownloader.presenter.interfaces.ISearchResultPresenter
import com.jueggs.stackdownloader.view.*
import com.jueggs.utils.base.BasePresenter
import com.jueggs.utils.extension.isNetworkConnected
import org.mapstruct.factory.Mappers
import javax.inject.Inject

class SearchResultPresenter : BasePresenter<SearchResultView, SearchResultViewModel>(), ISearchResultPresenter {
    @Inject
    lateinit var app: App
    @Inject
    lateinit var dataProvider: DataProvider
    @Inject
    lateinit var daoSession: DaoSession
    @Inject
    lateinit var rendererFactory: RendererFactory
    private val questionMapper = Mappers.getMapper(QuestionMapper::class.java)
    private val answerMapper = Mappers.getMapper(AnswerMapper::class.java)
    private val ownerMapper = Mappers.getMapper(OwnerMapper::class.java)

    init {
        App.applicationComponent.inject(this)
    }

    override fun onStartSearch(searchCriteria: SearchCriteria) {
        dataProvider.fetchQuestions(searchCriteria, { itemShellData ->
            val questionBos = itemShellData.mapToBo().items.map { it.mapToBo() }

            val renderer = rendererFactory.createRenderer<Question>()
            questionBos.forEach { renderer.render(it) }

            viewModel.questions = questionBos
            view.displayQuestions(questionBos)
            if (viewModel.questions.any()) view.enableDownloadButton() else view.disableDownloadButton()
        }, { errorMessage ->
            view.showLongToast(errorMessage)
        })
    }

    override fun onHomeButtonClick() = view.showSearchResult()

    override fun onQuestionClick(question: Question) {
        if (app.isNetworkConnected()) {
            dataProvider.fetchAnswers(arrayListOf(question.questionId), { itemShellData ->
                val answerBos = itemShellData.mapToBo().items.map { it.mapToBo() }

                val questionRenderer = rendererFactory.createRenderer<Question>()
                val answerRenderer = rendererFactory.createRenderer<Answer>()
                questionRenderer.render(question)
                answerBos.forEach { answerRenderer.render(it) }

                viewModel.answers = answerBos
                viewModel.question = question
                view.displayAnswers(question, answerBos)
                view.showToolbarHomeButton()
            }, { errorMessage ->
                view.showLongToast(errorMessage)
            })
        } else
            view.showLongToast(R.string.error_no_network)
    }

    override fun onDownload() {
        if (app.isNetworkConnected() && viewModel.questions.any()) {
            dataProvider.fetchAnswers(viewModel.questions.map { it.questionId },
                    { itemShellData ->
                        val answerBos = itemShellData.mapToBo().items.map { it.mapToBo() }

                        daoSession.questionEntityDao.deleteAll()
                        daoSession.answerEntityDao.deleteAll()
                        daoSession.ownerEntityDao.deleteAll()

                        daoSession.database.beginTransaction()

                        val questionEntities = viewModel.questions.map(questionMapper::mapBoToEntity)
                        val answerEntities = answerBos.map(answerMapper::mapBoToEntity)

                        var ownerBos = viewModel.questions.map { it.owner }
                        ownerBos.toMutableList().addAll(answerBos.map { it.owner })
                        ownerBos = ownerBos.distinctBy { it.userId }
                        val ownerEntities = ownerBos.map(ownerMapper::mapBoToEntity)

//                        daoSession.questionEntityDao.insertInTx(questionEntities)
//                        daoSession.answerEntityDao.insertInTx(answerEntities)
//                        daoSession.ownerEntityDao.insertInTx(ownerEntities)

                        loopQuestions@ for (i in 0..questionEntities.size) {
                            val question = questionEntities[i]
                            val questionOwner = ownerEntities.singleOrNull { it.id == question.ownerId } ?: continue@loopQuestions

                            daoSession.questionEntityDao.insert(question)
                            daoSession.ownerEntityDao.insertOrReplace(questionOwner)

                            question.owner = questionOwner
                            questionOwner.questions.add(question)

                            val answers = answerEntities.filter { it.questionId == question.id }
                            loopAnswers@ for (j in 0..answers.size) {
                                val answer = answerEntities[j]
                                val answerOwner = ownerEntities.singleOrNull { it.id == answer.ownerId } ?: continue@loopAnswers

                                daoSession.answerEntityDao.insert(answer)
                                daoSession.ownerEntityDao.insertOrReplace(answerOwner)

                                answer.owner = answerOwner
                                answer.question = question
                                answerOwner.answers.add(answer)
                                question.answers.add(answer)
                            }
                        }

//                        val questionEntities = viewModel.questions.map { question ->
//                            val questionEntity = questionMapper.mapBoToEntity(question)
//                            val answerEntities = answerBos.filter { it.questionId == question.questionId }.map(answerMapper::mapBoToEntity)
//                            val ownerEntity = ownerMapper.mapBoToEntity(question.owner)
//
//                            daoSession.ownerEntityDao.insertOrReplace(ownerEntity)
//
//                            questionEntity.owner = ownerEntity
//                            ownerEntity.questions.add(questionEntity)
//
//                            daoSession.questionEntityDao.insertOrReplace(questionEntity)
//
//                            questionEntity
//                        }
//
//                        val answerEntities = answerBos.map {
//                            val ownerEntity = ownerMapper.mapBoToEntity(it.owner)
//                            daoSession.ownerEntityDao.insertOrReplace(ownerEntity)
//
//                            val answerEntity = answerMapper.mapBoToEntity(it)
//                            answerEntity.owner = ownerEntity
//                            ownerEntity.answers.add(answerEntity)
//                            answerEntity
//                        }


                        try {
                            daoSession.database.setTransactionSuccessful()
                            view.showLongToast(ctx.getString(R.string.toast_download_successful))
                        } catch (e: Exception) {
                            //TODO libupdate
                            val msg = ctx.getString(R.string.error_saving_failed, e.message)
                            view.showLongToast(msg)
                        } finally {
                            daoSession.database.endTransaction()
                        }
                    },
                    { errorMessage ->
                        view.showLongToast(errorMessage)
                    })
        } else
            view.showLongToast(R.string.error_no_network)
    }

    override fun viewStub(): SearchResultView = SearchResultViewStub()
}
