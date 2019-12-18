$(document).ready(function () {

    /** MODAL CLOSE -> RESET FORM **/
    $("#change_password_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /** RESET FORM **/
    function resetForm() {
        $("#change_password_form").validate().resetForm();
    }

    /** CLICK BUTTON RESET **/
    $("#cp_reset").click(function () {
        resetForm();
    });

    /** LOADING **/
    function loading() {
        /** LOADING SPINNER **/
        $("#cp_spinner").removeClass("fa fa-save");
        $("#cp_spinner").addClass("fa fa-spinner fa-spin");
        $("#cp_submit").prop("disabled", true);
        $("#cp_reset").prop("disabled", true);
    }

    /** UNLOADING **/
    function unloading() {
        /** LOADING SPINNER **/
        $("#cp_spinner").removeClass("fa fa-spinner fa-spin");
        $("#cp_spinner").addClass("fa fa-save");
        $("#cp_submit").prop("disabled", false);
        $("#cp_reset").prop("disabled", false);
    }

    /** VALIDATE PASSWORD METHOD **/
    $.validator.addMethod("password_must", function (password, element) {
        var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$/i;
        return this.optional(element) || regex.test(password);
    });

    /** VALIDATE PASSWORD USING **/
    $.validator.addMethod("password_using", function(password, element) {
        var ajax_check = $.ajax({
            type: "PUT",
            contentType: "application/json",
            dataType: "json",
            url: "/PasswordUsing",
            data: password,
            async: false
        });
        var using = false;
        ajax_check.done(function (data) {
            using = data;
        });
        return this.optional(element) || !using;
    });

    /** VALIDATE REGISTER FORM **/
    $("#change_password_form").validate({
        /** RULES **/
        rules: {
            /** PASSWORD REQUIRED **/
            cp_password: {
                required: true,
                minlength: 8,
                maxlength: 32,
                password_must: true,
                password_using: true
            },
            /** CONFIRM PASSWORD REQUIRED **/
            cp_confirm_password: {
                required: true,
                equalTo: "#cp_password"
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            cp_password: {
                required: "New password is required!",
                minlength: "New password must be more than 8 character!",
                maxlength: "New password must be less than 32 character!",
                password_must: "Passwords must contain including uppercase, lowercase letters and numbers",
                password_using: "The password you have entered has been previously used, please use another password"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            cp_confirm_password: {
                required: "Confirm password is required!",
                equalTo: "Password and Password confirm must be same!"
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
            var password = $("#cp_password").val();

            /**
             *  AJAX CALL ChangePassword CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_update = $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/ChangePassword",
                data: password,
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
            text: "Password change successfully!",
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
                $("#change_password_modal").modal("hide");
                /** RESET FORM SEND EMAIL FORGOT PASSWORD **/
                resetForm();
                /** REDIRECT TO INDEX **/
                window.location.replace("/");
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