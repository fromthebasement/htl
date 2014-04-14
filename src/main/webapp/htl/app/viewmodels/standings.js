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
            { name: "Andy", score: "6" },
            { name: "Becky", score: "4" },
            { name: "Dan", score: "4" },
            { name: "Grant", score: "0" },
            { name: "Jeff", score: "2" },
            { name: "John", score: "0" },
            { name: "Kevin", score: "4" },
            { name: "Matt", score: "4" },
            { name: "Ryan S.", score: "4" },
            { name: "Todd", score: "4" }
        ];

        var league2standings = [
            { name: "Eliott", score: "3" },
            { name: "Isabel", score: "0" },
            { name: "Jack", score: "0" },
            { name: "Jim", score: "0" },
            { name: "Kelly", score: "5" },
            { name: "Maya", score: "0" },
            { name: "Merrell", score: "6" },
            { name: "Nolan", score: "1" },
            { name: "Ryan", score: "4" }

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