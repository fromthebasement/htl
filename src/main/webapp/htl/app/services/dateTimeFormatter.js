define(['moment'], function(moment) {
    /* Returns a formatted date and time
     *
     * @param date      Any string that can be parsed by Date.parse
     * @param options {
     *      format: Format of date to return, default is "M/D/YY h:mma" (e.g. 09/06/2014 1:35pm )
     * }
     *
     * @return {*}
     */
    function fromInterchangeDateUTC( date, options ) {
        if(!date)
            return "";

        options = options || {};
        options.format = options.format || "MM/DD/YYYY h:mma";

        return moment(date).format(options.format);
    }

    function toInterchangeDateUTC( date, options ) {
        return moment(date,options.format).utc().format();
    }

    return{
        toInterchangeDate: toInterchangeDateUTC,
        fromInterchangeDate: fromInterchangeDateUTC
    };
});