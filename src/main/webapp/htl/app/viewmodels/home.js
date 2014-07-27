define(function() {
    "use strict";

    var router = require('plugins/router'),
        rest = require('rest');

    var ctor = function () {
        this.displayName = 'Questions';
        this.links = [{
            caption: "Questions",
            href: "#surveyResponses"
        },{
            caption: "Survey Feeds",
            href: "#surveyFeeds"
        }];
    };

    return ctor;
});