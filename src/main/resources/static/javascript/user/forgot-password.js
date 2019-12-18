$(document).ready(function () {

    /** MODAL CLOSE -> RESET FORM **/
    $("#forgot_password_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /** RESET FORM **/
    function resetForm() {
        $("#forgot_password_form").validate().resetForm();
        $("#fp_email_address").empty();
        not_found_hide();
    }

    /** HIDE MESSAGE NOT FOUND USERNAME **/
    function not_found_hide() {
        $("#fp_not_found").addClass("hide");
        $("#fp_not_found").removeClass("show");
    }

    /** SHOW MESSAGE NOT FOUND USERNAME **/
    function not_found_show() {
        $("#fp_not_found").addClass("show");
        $("#fp_not_found").removeClass("hide");
    }

    /** CLICK BUTTON RESET **/
    $("#fp_reset").click(function () {
        resetForm();
    });

    /**
     *  USERNAME BLUR EVENT
     *  FIND EMAIL BY USERNAME
     */
    var emailAddress = "";
    $("#fp_app_username").blur(function () {
        var appUsername = $("#fp_app_username").val();
        if(appUsername != null && (appUsername = appUsername.trim()) != "") {
            var length = appUsername.length;
            if(8 <= length && length <= 32) {
                var ajax_exists = $.ajax({
                    type: "GET",
                    contentType: "application/json",
                    url: "/FindEmailAddressByAppUsername",
                    data: {
                        appUsername: appUsername
                    },
                    dataType: "json",
                    async: false
                });
                ajax_exists.done(function (data) {
                    emailAddress = data;
                    if (data != null && data != "") {
                        $("#fp_email_address").text(data.substring(0, 4) + "##########" + data.substring(data.length - 6, data.length));
                        /** HIDE MESSAGE NOT FOUND EMAIL ADDRESS **/
                        not_found_hide();
                    }
                    else {
                        $("#fp_email_address").empty();
                        /** SHOW MESSAGE NOT FOUND EMAIL ADDRESS **/
                        not_found_show();
                    }
                });
            }
        }
    });

    /** VALIDATE EMAIL CONFIRM SAME EMAIL METHOD **/
    $.validator.addMethod("fp_email_address_match", function (confirmEmailAddress, element) {
        return this.optional(element) || (confirmEmailAddress != null && confirmEmailAddress === emailAddress);
    });

    /** LOADING **/
    function loading() {
        /** LOADING SPINNER **/
        $("#fp_spinner").removeClass("fa fa-paper-plane");
        $("#fp_spinner").addClass("fa fa-spinner fa-spin");
        $("#fp_submit").prop("disabled", true);
        $("#fp_reset").prop("disabled", true);
    }

    /** UNLOADING **/
    function unloading() {
        /** LOADING SPINNER **/
        $("#fp_spinner").removeClass("fa fa-spinner fa-spin");
        $("#fp_spinner").addClass("fa fa-paper-plane");
        $("#fp_submit").prop("disabled", false);
        $("#fp_reset").prop("disabled", false);
    }

    /** VALIDATE REGISTER FORM **/
    $("#forgot_password_form").validate({
        /** RULES **/
        rules: {
            /** USERNAME REQUIRED **/
            fp_app_username: {
                required: true,
                minlength: 8,
                maxlength: 32
            },
            /** EMAIL ADDRESS REQUIRED **/
            fp_confirm_email_address: {
                required: true,
                email: true,
                fp_email_address_match: true
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            fp_app_username: {
                required: "Username is required!",
                minlength: "Username must be more than 8 character!",
                maxlength: "Username must be less than 32 character!"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            fp_confirm_email_address: {
                required: "Email address is required!",
                email: "Please enter a valid email address!",
                fp_email_address_match: "The email you entered is not the email you registered!"
            }
        },
        /**
         *  SUBMIT FORM
         */
        submitHandler: function (form) {
            /** LOADING SPINNER **/
            loading();
            /**
             *  GET FORM VALUE
             */
            var appUser = new Object();
            appUser.appUsername = $("#fp_app_username").val();

            var appUserProfile = new Object();
            appUserProfile.emailAddress = emailAddress;

            appUser.appUserProfile = appUserProfile;

            /**
             *  AJAX CALL SendEmailForgotPassword CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_update = $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/SendEmailForgotPassword",
                data: JSON.stringify(appUser),
                dataType: "json"
            });
            /** AJAX CALL SUCCESS **/
            ajax_update.done(function (data) {
                /** UNLOADING SPINNER **/
                unloading();
                data? alertSuccess() : alertError();
            });
        }

    });

    /** ALERT MESSAGE SUCCESS ACTION **/
    function alertSuccess() {
        swal({
            title: "Action Completed!",
            text: "Please check your email address to receive new password!",
            icon: "success",
            button: {
                text: "OK",
                closeModal: true,
                visible: true,
                className: "btn btn-success btn-lg"
            }
        })
        .then(function(isOk) {
            if(isOk) {
                /** CLOSE MODAL SEND EMAIL FORGOT PASSWORD **/
                $("#forgot_password_modal").modal("hide");
                /** RESET FORM SEND EMAIL FORGOT PASSWORD **/
                resetForm();
                /** OPEN MODAL SIGN-IN **/
                $("#sign_in_app_user_modal").modal("show");
            }
        });
    }

    /** ALERT MESSAGE ERROR ACTION **/
    function alertError() {
        swal({
            title: "Action completed!",
            text: "Error occurred, please try again!",
            icon: "error",
            button: {
                text: "OK",
                closeModal: true,
                visible: true,
                className: "btn btn-danger btn-lg"
            }
        });
    }

});