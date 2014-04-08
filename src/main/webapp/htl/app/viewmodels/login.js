define(function(require, exports, module) {
    var router = require('plugins/router'),
        appViewModel = require('viewmodels/appViewModel'),
        shell = require('viewmodels/shell'),
        rest = require('rest');

    var model = {
            userName: "",
            password: ""
        },
        _viewModel = ko.mapping.fromJS(model),
        _extended = {
            error: ko.observable(""),
            redirectURL: null
        },
        viewModel = $.extend( _viewModel, _extended);

    function activate(redirect){
        viewModel.redirectURL = redirect;
        shell.showNavBar(false);
    }

    function deactivate(){
        shell.showNavBar(true);
    }

    function login(evt) {
        //Send an ajax request
        var data = ko.mapping.toJS(viewModel);

        rest.user.login({
            data: data
        }).done(function(data, status, xhr) {
            appViewModel.user(data.user);

            if( viewModel.redirectURL )  {
                router.navigate(viewModel.redirectURL);
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
        deactivate: deactivate,
        login: login
    } );

    return viewModel;
});