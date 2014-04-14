define(function() {
    var ctor = function () {
        this.displayName = 'We\'re winning, bitches!';
        this.description = 'Playing HTL makes you smarter, richer, and more attractive.';

        function sortStandings(standings) {
            return standings.sort(function(a,b){
                return b.score - a.score;
            });
        }

        var league1standings = [
            { name: "Andy", score: "0" },
            { name: "Becky", score: "0" },
            { name: "Dan", score: "0" },
            { name: "Grant", score: "0" },
            { name: "John", score: "0" },
            { name: "Kevin", score: "0" },
            { name: "Matt", score: "0" },
            { name: "Ryan M.", score: "0" },
            { name: "Ryan S.", score: "0" },
            { name: "Todd", score: "0" }
        ];

        var league2standings = [
            { name: "Isabel", score: "0" },
            { name: "Merrell", score: "0" },
            { name: "Nolan", score: "0" }
        ];

        this.sortedStandings = [
            sortStandings(league1standings),
            sortStandings(league2standings)
        ]
    };

    //Note: This module exports a function. That means that you, the developer, can create multiple instances.
    //This pattern is also recognized by Durandal so that it can create instances on demand.
    //If you wish to create a singleton, you should export an object instead of a function.
    //See the "flickr" module for an example of object export.

    return ctor;
});