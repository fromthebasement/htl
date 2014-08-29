define(function(require){
    require('jquery.datetimepicker');

    var moment = require('moment');

    Date.parseDate = function( input, format ){
        return moment(input,format).toDate();
    };
    Date.prototype.dateFormat = function( format ){
        return moment(this).format(format);
    };
});
