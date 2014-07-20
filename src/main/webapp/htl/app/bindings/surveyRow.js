/*
 * Renders a survey row
 * syntax:
 *      surveyRow: survey
 */
define(function(require, exports, module) {
    var ko = require('knockout'),
        rest = require('rest'),
        template = require('text!bindings/surveyRow.html');

    ko.bindingHandlers.surveyRow = {
        init: function (element, valueAccessor, allBindingsAccessor, viewModel, bindingContext) {
            var $element = $(element);

            var survey = valueAccessor();

            survey.active.subscribe(function(newValue){
                updateSurvey(survey);
            });

            // Insert the markup template
            $element.html( $(template) );
        }
    };

    function updateSurvey(survey) {
        return rest.surveys.update({
            data: ko.mapping.toJS(survey)
        });
    }
});