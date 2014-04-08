define(function(require, exports, module) {
    //User state
    var _viewModel = {
        user : ko.observable({
            id: null,
            name: null,
            username: null
        })
    };

    return _viewModel;
});