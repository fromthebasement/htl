define(function() {
    var router = require('plugins/router'),
        rest = require('rest');

    var ctor = function () {
        this.displayName = 'Author';
        var surveyFeeds = ko.observableArray();
        this.surveyFeeds = surveyFeeds;

        function activate(){
            return rest.surveyFeeds.getAll().done(function(data){
                surveyFeeds(data);
            });
        }

        this.activate = activate;
    };

    //Note: This module exports a function. That means that you, the developer, can create multiple instances.
    //This pattern is also recognized by Durandal so that it can create instances on demand.
    //If you wish to create a singleton, you should export an object instead of a function.
    //See the "flickr" module for an example of object export.

    return ctor;
});