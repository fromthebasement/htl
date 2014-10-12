define(function(require) {
    var router = require('plugins/router'),
        rest = require('rest');

    var ctor = function () {
        this.displayName = 'Questions';
        var surveyResponses = ko.observableArray();
        this.surveyResponses = surveyResponses;

        function activate(leagueID){
            var league = {
                id: leagueID
            };

            return rest.surveyResponses.getActive({
                league: league,
                selector: "leaguePlayer(league(name),player(name)),survey(name,deadline)"
            }).done(function(data){
                surveyResponses(data);
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