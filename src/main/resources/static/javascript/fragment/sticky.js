$(document).ready(function () {

    /** CUSTOM FUNCTION WHICH TOGGLES BETWEEN STICKY CLASS (is-sticky) **/
    var stickyToggle = function (sticky, stickyWrapper, scrollElement) {
        var stickyHeight = sticky.outerHeight();
        var stickyTop = stickyWrapper.offset().top;
        if (scrollElement.scrollTop() >= stickyTop) {
            stickyWrapper.height(stickyHeight);
            sticky.addClass("is-sticky");
        } else {
            sticky.removeClass("is-sticky");
            stickyWrapper.height("auto");
        }
    };

    /** FIND ALL data-toggle="sticky-onscroll" ELEMENTS **/
    $("[data-toggle='sticky-onscroll']").each(function () {
        var sticky = $(this);
        var stickyWrapper = $("<div>").addClass("sticky-wrapper"); /** INSERT HIDDEN ELEMENT TO MAINTAIN ACTUAL TOP OFFSET ON PAGE **/
        sticky.before(stickyWrapper);
        sticky.addClass("sticky");

        /** SCROLL & RESIZE EVENTS **/
        $(window).on("scroll.sticky-onscroll resize.sticky-onscroll", function () {
            stickyToggle(sticky, stickyWrapper, $(this));
        });

        /** ON PAGE LOAD **/
        stickyToggle(sticky, stickyWrapper, $(window));
    });

});