define(function(require) {
    var router = require('plugins/router'),
        rest = require('rest'),
        utils = require('utils'),
        dateTimeFormatter = require('dateTimeFormatter'),
        surveySelector = "name,questions(name,correctAnswer,answers(name)),deadline,surveyFeed(name),active";


    var ctor = function () {
        var _this = this;
        this.displayName = 'Survey';
        var survey = ko.mapping.fromJS({
            id: "",
            name: "",
            questions: [],
            deadline: "",
            active: ""
        });

        this.survey = survey;

        this.canEdit = ko.computed(function(){
            return !survey.active();
        });

        this.publish = function(){
            survey.active(true);
        };

        this.makeDraft = function(){
            survey.active(false);
        };

        var format = 'M/D/YY h:mma';

        survey.formattedDeadline =  ko.computed({
            read: function() {
                return dateTimeFormatter.fromInterchangeDate(ko.unwrap(survey.deadline),{format:format});
            },
            write: function(value){
                var interchangeDate = dateTimeFormatter.toInterchangeDate(value,{format:format});
                survey.deadline(interchangeDate);
            }
        });

        function mapSurveyData(data){
            var mapping = {
                'questions': {
                    create: function (options) {
                        return mapQuestion(options.data);
                    }
                }
            };

            ko.mapping.fromJS(data, mapping, survey);
        }

        function mapQuestion(data) {
            data = $.extend({
                name: "",
                id: "",
                answers: [],
                correctAnswer: null
            }, data);

            var mapped = ko.mapping.fromJS(data);

            var correctAnswer = mapped.correctAnswer;
            if (ko.unwrap(correctAnswer) && correctAnswer.id()) {
                $.each(mapped.answers(), function (index, answer) {
                    if (answer.id() === correctAnswer.id()) {
                        correctAnswer = answer;
                        return false;
                    }
                })
            }

            mapped.correctAnswer = ko.observable(correctAnswer);

            return mapped;
        }

        function activate(id) {
            return rest.surveys.get({
                id: id,
                selector: surveySelector
            }).done(function (data) {
                mapSurveyData(data);

                function updateSurvey(){
                    var data = ko.mapping.toJS(survey);
                    delete data.surveyFeed;

                    return rest.surveys.update({
                        data: data,
                        selector: surveySelector
                    }).done(mapSurveyData);
                }

                function publishSurvey(){
                    var data = ko.mapping.toJS(survey);
                    delete data.surveyFeed;

                    return rest.surveys.publish({
                        data: data,
                        selector: surveySelector
                    }).done(mapSurveyData);
                }

                survey.name.subscribe(updateSurvey);
                survey.deadline.subscribe(updateSurvey);

                survey.active.subscribe(publishSurvey);
            });
        }

        this.activate = activate;

        function addQuestion(survey) {
            rest.questions.create({
                selector: 'name,answers(name)',
                data: {
                    name: "New Question",
                    survey: {
                        id: survey.id()
                    },
                    answers: []
                }
            }).done(function (data) {
                var question = mapQuestion(data);
                survey.questions.push(question);
            });
        }

        this.addQuestion = addQuestion;

        function removeQuestion(question) {
            var context = ko.contextFor(event.target),
                survey = context.$parent;

            rest.questions.delete({
                data: ko.mapping.toJS(question)
            }).done(function (data) {
                survey.questions.remove(question);
            });
        }

        this.removeQuestion = removeQuestion;

        function addAnswer(question) {
            rest.answers.create({
                selector: 'name',
                data: {
                    name: "New Answer",
                    question: ko.mapping.toJS(question)
                }
            }).done(function (data) {
                question.answers.push(ko.mapping.fromJS(data));
            });
        }

        this.addAnswer = addAnswer;

        function removeAnswer(answer, event) {
            var context = ko.contextFor(event.target),
                question = context.$parent;

            rest.answers.delete({
                data: ko.mapping.toJS(answer)
            }).done(function (data) {
                question.answers.remove(answer);
            });
        }

        this.removeAnswer = removeAnswer;

        function selectName(element, index, data) {
            var $element = $(element);
            utils.selectElementContents($element.find('.name').get(0));
        }

        this.selectName = selectName;
    }

    return ctor;
});