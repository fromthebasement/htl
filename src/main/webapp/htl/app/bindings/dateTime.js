define(function(require){
    "use strict";

    require('knockout');
    var dateTimeFormatter = require('dateTimeFormatter');


    ko.bindingHandlers.dateTime = {
        update: function (element, valueAccessor, allBindingsAccessor, viewModel) {
            var value = valueAccessor();
            var valueUnwrapped = ko.utils.unwrapObservable(value);

            if (!valueUnwrapped)
                return;

            var formattedDate = dateTimeFormatter.fromInterchangeDate( valueUnwrapped );

            $(element).text(formattedDate);
        }
    }
});