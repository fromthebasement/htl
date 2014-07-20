/*
 * Renders a question
 * syntax:
 *      question: question
 */
define(function(require, exports, module) {
    var ko = require('knockout'),
        rest = require('rest'),
        template = require('text!bindings/answer.html');

    ko.bindingHandlers.answer = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var $element = $(element);

            $element.addClass('answer');

            var answer = valueAccessor();

            answer.name.subscribe(function(newValue){
                updateAnswer(answer);
            });

            // Insert the markup template
            $element.html( $(template) );
        }
    };

    function updateAnswer(question) {
        return rest.answers.update({
            data: ko.mapping.toJS(question)
        });
    }
});