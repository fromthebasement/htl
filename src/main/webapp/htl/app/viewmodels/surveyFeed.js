define(function() {
    var router = require('plugins/router'),
        rest = require('rest');

    var ctor = function () {
        var _this = this;
        this.displayName = 'Survey Feed';
        this.surveyFeed = ko.observable({});

        function activate(id){
            rest.surveyFeeds.get({
                id: id,
                selector: 'surveys(id),users(fullName)'
            }).done(function(data){
                _this.surveyFeed(data);
            });
        }

        this.activate = activate;
    };

    return ctor;
});