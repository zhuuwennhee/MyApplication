$(document).ready(function () {

    /** MODAL CLOSE -> RESET FORM **/
    $("#send_email_unlock_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /** RESET FORM **/
    function resetForm() {
        $("#send_email_unlock_form").validate().resetForm();
        $("#seu_not_found").addClass("hide");
        $("#seu_not_found").removeClass("show");
    }

    /** CLICK BUTTON RESET **/
    $("#seu_reset").click(function () {
        resetForm();
    });

    /**
     *  USERNAME BLUR EVENT
     *  FIND EMAIL BY USERNAME
     */
    var emailAddress = null;
    $("#seu_app_username").blur(function () {
        var appUsername = $("#seu_app_username").val();
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
                        $("#seu_email_address").text(data.substring(0, 4) + "##########" + data.substring(data.length - 6, data.length));
                        $("#seu_not_found").addClass("hide");
                        $("#seu_not_found").removeClass("show");
                        emailAddress = data;
                    }
                    else {
                        $("#seu_email_address").empty();
                        $("#seu_not_found").addClass("show");
                        $("#seu_not_found").removeClass("hide");
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
    $("#send_email_unlock_form").validate({
        /** RULES **/
        rules: {
            /** USERNAME REQUIRED **/
            seu_app_username: {
                required: true,
                minlength: 8,
                maxlength: 32
            },
            /** EMAIL ADDRESS REQUIRED **/
            seu_confirm_email_address: {
                required: true,
                email: true,
                confirm_email_address: true
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            seu_app_username: {
                required: "Username is required!",
                minlength: "Username must be more than 8 character!",
                maxlength: "Username must be less than 32 character!"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            seu_confirm_email_address: {
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
            appUser.appUsername = $("#seu_app_username").val();

            var appUserProfile = new Object();
            appUserProfile.emailAddress = emailAddress;

            appUser.appUserProfile = appUserProfile;

            /**
             *  AJAX CALL CreateAppUser CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_seu = $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/SendEmailUnlockAccount",
                data: JSON.stringify(appUser),
                dataType: "json"
            });
            /** AJAX CALL SUCCESS **/
            ajax_seu.done(function (data) {
                data? alertSuccess() : alertError();
            });
        }

    });

    /** ALERT MESSAGE SUCCESS ACTION **/
    function alertSuccess() {
        swal({
            title: "Action Completed!",
            text: "Please check your email address to unlock your account!",
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
                /** CLOSE MODAL SEND EMAIL UNLOCK ACCOUNT **/
                $("#send_email_unlock_modal").modal("hide");
                /** RESET FORM SEND EMAIL UNLOCK ACCOUNT **/
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