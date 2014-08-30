/*
 * Renders a question
 * syntax:
 *      question: question
 */
define(function(require, exports, module) {
    var ko = require('knockout'),
        rest = require('rest'),
        template = require('text!bindings/question.html');

    ko.bindingHandlers.question = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var $element = $(element);

            $element.addClass('question');

            var question = valueAccessor();

            question.name.subscribe(function(newValue){
                updateQuestion(question);
            });

            question.correctAnswer.subscribe(function(newValue){
                updateQuestion(question);
            });

            // Insert the markup template
            $element.html( $(template) );
        }
    };

    function updateQuestion(question) {
        return rest.questions.update({
            data: ko.mapping.toJS(question)
        });
    }
});