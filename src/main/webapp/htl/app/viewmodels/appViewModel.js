define(function(require, exports, module) {
    var emptyUser = {
        id: null,
        fullName: null,
        username: null,
        roleList: [],
        leaguePlayer: ko.observable({
            league: {
                id: null
            },
            player: {}
        }),
        leaguePlayers: ko.observableArray([])
    };

    //User state
    var _viewModel = {
        user : ko.observable(emptyUser)
    };

    function reset() {
        _viewModel.user(emptyUser);
    }

    var isAdmin = ko.computed(function(){
        var user = _viewModel.user();

        var isAdmin = false;

        if( user.roleList )
        {
            $.each(user.roleList, function(index,role){
                if( role.value === 'ROLE_ADMIN' ) {
                    isAdmin = true;
                    return;
                }
            });
        }

        return isAdmin;
    });

    function setUser(user) {
        var _user = $.extend({}, emptyUser, user);
        if( !ko.isObservable(user.leaguePlayer))
            _user.leaguePlayer = ko.observable(_user.leaguePlayer);

        _viewModel.user(_user);
    }

    function setLeaguePlayer(leaguePlayer,evt){
        _viewModel.user().leaguePlayer(leaguePlayer);
        return true;
    }

    return $.extend(_viewModel, {
        isAdmin: isAdmin,
        reset: reset,
        setUser: setUser,
        setLeaguePlayer: setLeaguePlayer
    });
});