$(document).ready(function () {

    /** MODAL CLOSE -> RESET FORM **/
    $("#resend_confirmation_token_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /** RESET FORM **/
    function resetForm() {
        $("#resend_confirmation_token_form").validate().resetForm();
        $("#rct_not_found").addClass("hide");
        $("#rct_not_found").removeClass("show");
    }

    /** CLICK BUTTON RESET **/
    $("#rct_reset").click(function () {
        resetForm();
    });

    /**
     *  USERNAME BLUR EVENT
     *  FIND EMAIL BY USERNAME
     */
    var emailAddress = null;
    $("#rct_app_username").blur(function () {
        var appUsername = $("#rct_app_username").val();
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
                    if (data != null && data != "") {
                        $("#rct_email_address").text(data.substring(0, 4) + "##########" + data.substring(data.length - 6, data.length));
                        $("#rct_not_found").addClass("hide");
                        $("#rct_not_found").removeClass("show");
                        emailAddress = data;
                    }
                    else {
                        $("#rct_email_address").empty();
                        $("#rct_not_found").addClass("show");
                        $("#rct_not_found").removeClass("hide");
                    }
                });
            }
        }
    });

    /** VALIDATE EMAIL CONFIRM SAME EMAIL METHOD **/
    $.validator.addMethod("confirm_email_address", function (confirmEmailAddress, element) {
        return this.optional(element) || (confirmEmailAddress != null && confirmEmailAddress.trim() == emailAddress);
    });

    /** VALIDATE REGISTER FORM **/
    $("#resend_confirmation_token_form").validate({
        /** RULES **/
        rules: {
            /** USERNAME REQUIRED **/
            rct_app_username: {
                required: true,
                minlength: 8,
                maxlength: 32
            },
            /** EMAIL ADDRESS REQUIRED **/
            rct_confirm_email_address: {
                required: true,
                email: true,
                confirm_email_address: true
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            rct_app_username: {
                required: "Username is required!",
                minlength: "Username must be more than 8 character!",
                maxlength: "Username must be less than 32 character!"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            rct_confirm_email_address: {
                required: "Email is required!",
                email: "Please enter a valid email address!",
                confirm_email_address: "The email you entered is not the email you registered!"
            }
        },
        /**
         *  SUBMIT FORM
         */
        submitHandler: function (form) {
            /**
             *  GET FORM VALUE
             */
            var appUser = new Object();
            appUser.appUsername = $("#rct_app_username").val();

            var appUserProfile = new Object();
            appUserProfile.emailAddress = emailAddress;

            appUser.appUserProfile = appUserProfile;

            /**
             *  AJAX CALL CreateAppUser CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_update = $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/ResendConfirmationToken",
                data: JSON.stringify(appUser),
                dataType: "json"
            });
            /** AJAX CALL SUCCESS **/
            ajax_update.done(function (data) {
                data? alertSuccess() : alertError();
            });
        }

    });

    /** ALERT MESSAGE SUCCESS ACTION **/
    function alertSuccess() {
        swal({
            title: "Action Completed!",
            text: "Please check your email address confirm registration!",
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
                /** CLOSE MODAL RESEND EMAIL CONFIRMATION TOKEN **/
                $("#resend_confirmation_token_modal").modal("hide");
                /** RESET FORM RESEND EMAIL CONFIRMATION TOKEN VALUE **/
                resetForm();
                /** OPEN MODAL SIGN-IN ACCOUNT **/
                $("#sign_in_account_modal").modal("show");
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