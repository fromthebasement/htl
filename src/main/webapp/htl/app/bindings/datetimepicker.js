/*
 * Instantiate a datetimepicker on an element
 * syntax:
 *      question: question
 */
define(function(require, exports, module) {
    require('bootstraps/datetimepicker-bootstrap');

    var ko = require('knockout'),
        dateTimeFormatter = require('dateTimeFormatter');

    ko.bindingHandlers.datetimepicker = {
        init: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
            var val = valueAccessor();

            ko.bindingHandlers.value.init(element, valueAccessor,
                allBindings, viewModel, bindingContext);

            $(element).datetimepicker({
                format: 'M/D/YY h:mma',
                formatTime: 'h:mma',
                formatDate: 'MM/DD/YYYY',
                minDate: 0
            });
        },
        update: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
            ko.bindingHandlers.value.update(element, valueAccessor,
                allBindings, viewModel, bindingContext);
        }
    };
});