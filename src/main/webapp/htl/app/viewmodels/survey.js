define(function() {
    var router = require('plugins/router'),
        rest = require('rest');

    var ctor = function () {
        var _this = this;
        this.displayName = 'Survey';
        this.survey = ko.mapping.fromJS({
            id: "",
            name: "",
            questions: [],
            endTime: ""
        });

        function activate(id){
            return rest.surveys.get({
                id: id,
                selector: 'name,questions(name,answers(name)),endTime,surveyFeed(name)'
            }).done(function(data){
                ko.mapping.fromJS(data, {}, _this.survey);
            });
        }

        this.activate = activate;

        function addQuestion(survey){
            rest.questions.create({
                selector: 'name,answers(name)',
                data: {
                    name: "New Question",
                    survey: {
                        id: survey.id()
                    },
                    answers: []
                }
            }).done(function(data){
                var question = ko.mapping.fromJS( data);
                survey.questions.push(question);
            });
        }

        this.addQuestion = addQuestion;

        function removeQuestion(question) {
            var context = ko.contextFor(event.target),
                survey = context.$parent;

            rest.questions.delete({
                data: ko.mapping.toJS(question)
            }).done(function(data){
                survey.questions.remove(question);
            });
        }

        this.removeQuestion = removeQuestion;

        function addAnswer(question){
            rest.answers.create({
                selector: 'name',
                data: {
                    name: "New Answer",
                    question: ko.mapping.toJS(question)
                }
            }).done(function(data){
                question.answers.push(ko.mapping.fromJS(data));
            });
        }

        this.addAnswer = addAnswer;

        function removeAnswer(answer,event) {
            var context = ko.contextFor(event.target),
                question = context.$parent;

            rest.answers.delete({
                data: ko.mapping.toJS(answer)
            }).done(function(data){
                question.answers.remove(answer);
            });
        }

        this.removeAnswer = removeAnswer;
    };

    return ctor;
});