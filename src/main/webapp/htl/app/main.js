requirejs.config({
    paths: {
        'text': '../lib/require/text',
        'durandal':'../lib/durandal/js',
        'plugins' : '../lib/durandal/js/plugins',
        'transitions' : '../lib/durandal/js/transitions',
        'knockout': '../lib/knockout/knockout-3.1.0',
        'bootstrap': '../lib/bootstrap/js/bootstrap',
        'jquery': '../lib/jquery/jquery-2.1.0',
        'string-formatter': '../lib/string-formatter/string',
        'knockout-mapping': '../lib/knockout-mapping/knockout.mapping-2.4.1',
        'knockout-bootstrap': 'bootstraps/knockout-bootstrap',
        'rest': 'services/rest',
        'appViewModel': 'viewmodels/appViewModel'
    },
    shim: {
        'bootstrap': {
            deps: ['jquery'],
            exports: 'jQuery'
       }
    }
});

define(function(require, exports, module) {
    require('bootstrap');
    require('string-formatter');
    require('knockout-bootstrap');

    var system = require('durandal/system'),
        app = require('durandal/app'),
        viewLocator = require('durandal/viewLocator');

    //>>excludeStart("build", true);
    system.debug(true);
    //>>excludeEnd("build");

    app.title = 'Hometown League';

    app.configurePlugins({
        router:true,
        dialog: true,
        widget: true
    });

    app.start().then(function() {
        //Replace 'viewmodels' in the moduleId with 'views' to locate the view.
        //Look for partial views in a 'views' folder in the root.
        viewLocator.useConvention();

        //Show the app by setting the root view model for our application with a transition.
        app.setRoot('viewmodels/shell', 'entrance');

        // Collapse bootstrap nav bar when selecting an option from the nav
        $(document).on('click', '.collapse-on-select', function(){
            if($('.navbar-toggle').css('display') !='none'){
                setTimeout(function(){
                    $(".navbar-toggle").trigger( "click" );
                },0);
            }
        });
    });
});