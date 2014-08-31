define(function(require, exports, module) {
    var emptyUser = {
        id: null,
        fullName: null,
        username: null,
        roleList: [],
        leaguePlayer: {
            league: {
                id: null
            },
            player: {}
        }
    }

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

    return $.extend(_viewModel, {
        isAdmin: isAdmin,
        reset: reset
    });
});