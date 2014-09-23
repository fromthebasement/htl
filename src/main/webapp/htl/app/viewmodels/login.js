define(function(require, exports, module) {
    var router = require('plugins/router'),
        appViewModel = require('appViewModel'),
        rest = require('rest');

    var model = {
            username: "",
            password: ""
        },
        _viewModel = ko.mapping.fromJS(model),
        _extended = {
            error: ko.observable(""),
            redirectURL: null
        },
        viewModel = $.extend( _viewModel, _extended);

    function activate(){
    }

    function login(evt) {
        //Send an ajax request
        var data = ko.mapping.toJS(viewModel);

        rest.user.login({
            data: data,
            selector: "id,fullName,username,roleList,leaguePlayer(league(id,name),player(id,name))"
        }).done(function(data, status, xhr) {
            appViewModel.user(data);

            var redirectURL = viewModel.redirectURL;
            viewModel.redirectURL = null;

            if( redirectURL )  {
                router.navigate(redirectURL);
            }
            else {
                router.navigate('#');
            }
        }).fail(function(err) {
            //Check if the user provided bad credentials.
            if(err.status == 400)
                viewModel.error("Invalid email or password");
            else
                viewModel.error("An error occurred");
        });

        return false;
    }

    $.extend( viewModel, _extended, {
        activate: activate,
        login: login
    } );

    return viewModel;
});