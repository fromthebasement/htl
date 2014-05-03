// https://github.com/CalvinSlusarski/Knockout-handsontable
define(function(require, exports, module) {
    require("handsontable");
    require("knockout-handsontable");

    var ctor = function() { };

    ctor.prototype.activate = function(settings) {
        this.settings = settings;

        this.handsontableSettings = {
            data: settings.items(),
            colHeaders: ['Name', 'Score'],
            columns: [
                {data: 'name'},
                {data: 'score'}
            ],
            rowHeaders: true,
            columnHeaders: true,
            minSpareRows: 1
        }
    };

    return ctor;
});