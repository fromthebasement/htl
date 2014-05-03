define(function() {
    var router = require('plugins/router'),
        rest = require('rest');

    var ctor = function () {
        this.displayName = 'Competing!';
        var sortedStandings = ko.observableArray();
        this.sortedStandings = sortedStandings;

        function sortStandings(standings) {
            return standings.sort(function(a,b){
                return b.score - a.score;
            });
        }

        function activate(league){
            this.league = router.activeInstruction().config.settings.league;

            rest.league.getPlayers({
                league: this.league
            }).done(function(data){
                sortedStandings(sortStandings(data));
            });
        }

        this.activate = activate;
    };

    function getStandings( settings ){
        return rest.league.getPLayers(settings);
    }
    //Note: This module exports a function. That means that you, the developer, can create multiple instances.
    //This pattern is also recognized by Durandal so that it can create instances on demand.
    //If you wish to create a singleton, you should export an object instead of a function.
    //See the "flickr" module for an example of object export.

    return ctor;
});