$(document).ready(function () {

    /** CHECK/UNCHECK I READ AND AGREE -> DISABLE/ENABLE BUTTON SUBMIT **/
    $("#rau_read_and_agree").click(function () {
        /** GET STATUS CHECKBOX READ-AND-AGREE **/
        var raa = $("#rau_read_and_agree").is(":checked");
        /** AGREE CHECKED -> ENABLE SUBMIT BUTTON **/
        if (raa) {
            submit_enabled();
        }
        /** AGREE UNCHECKED -> DISABLE SUBMIT BUTTON **/
        else {
            submit_disabled();
        }
    });

    /** RESET FORM **/
    function resetForm() {
        /** RESET FORM VALUE **/
        $("#register_app_user_form").validate().resetForm();
        /** DISABLED BUTTON SUBMIT **/
        submit_disabled();
    }

    /** DISABLE SUBMIT **/
    function submit_disabled() {
        $("#rau_submit").addClass("disabled");
        $("#rau_submit").prop("disabled", true);
    }
    /** ENABLE SUBMIT **/
    function submit_enabled() {
        $("#rau_submit").removeClass("disabled");
        $("#rau_submit").prop("disabled", false);
    }

    /** CLICK BUTTON RESET **/
    $("#rau_reset").click(function () {
        resetForm();
    });

    /** MODAL CLOSE -> RESET FORM **/
    $("#register_app_user_modal").on("hide.bs.modal", function () {
        resetForm();
    });

    /**
     *  VALIDATE USERNAME EXISTS METHOD
     *  CHECK USERNAME EXISTS
     *  CALL UsernameExists CONTROLLER
     *  REQUEST PARAM username
     */
    $.validator.addMethod("app_username_exists", function (appUsername, element) {
        var ajax_exists = $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/AppUsernameExists",
            data: {
                appUsername: appUsername
            },
            dataType: "json",
            async: false
        });
        var exists = false;
        ajax_exists.done(function (data) {
            exists = data;
        });
        return this.optional(element) || !exists;
    });

    /** VALIDATE USERNAME MUST METHOD **/
    $.validator.addMethod("app_username_must", function (appUsername, element) {
        var regex = /^[a-z0-9]+$/i;
        return this.optional(element) || (isNaN(appUsername) && regex.test(appUsername));
    });

    /**
     *  VALIDATE EMAIL USED METHOD
     *  CHECK EMAIL ADDRESS USED
     *  AJAX CALL CONTROLLER EmailAddressUsed
     *  REQUEST PARAM emailAddress
     */
    $.validator.addMethod("email_address_used", function (emailAddress, element) {
        var ajax_read = $.ajax({
            type: "GET",
            contentType: "application/json",
            dataType: "json",
            url: "/EmailAddressUsed",
            data: {
                emailAddress: emailAddress
            },
            async: false
        });
        var used = false;
        ajax_read.done(function (data) {
            used = data;
        });
        return this.optional(element) || !used;
    });

    /** VALIDATE PASSWORD METHOD **/
    $.validator.addMethod("password_must", function (password, element) {
        var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$/i;
        return this.optional(element) || regex.test(password);
    });

    /** VALIDATE PHONE NUMBER **/
    $.validator.addMethod("vietnam_phone_number", function (phoneNumber, element) {
        var regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
        return this.optional(element) || regex.test(phoneNumber);
    });

    /** VALIDATE FIRST NAME METHOD **/
    $.validator.addMethod("person_name", function (name, element) {
        var regex = /^((?![0-9\~\!\@\#\$\%\^\&\*\(\)\_\+\=\-\[\]\{\}\;\:\"\\\/\<\>\?]).)+$/;
        return this.optional(element) || regex.test(name);
    });

    /** LOADING **/
    function loading() {
        /** LOADING SPINNER **/
        $("#rau_spinner").removeClass("fa fa-registered");
        $("#rau_spinner").addClass("fa fa-spinner fa-spin");
        $("#rau_submit").prop("disabled", true);
        $("#rau_reset").prop("disabled", true);
    }

    /** UNLOADING **/
    function unloading() {
        /** LOADING SPINNER **/
        $("#rau_spinner").removeClass("fa fa-spinner fa-spin");
        $("#rau_spinner").addClass("fa fa-registered");
        $("#rau_submit").prop("disabled", false);
        $("#rau_reset").prop("disabled", false);
    }

    /** VALIDATE REGISTER FORM **/
    $("#register_app_user_form").validate({
        /** RULES **/
        rules: {
            /** USERNAME REQUIRED, BETWEEN 8 - 32 CHARACTER **/
            rau_app_username: {
                required: true,
                minlength: 8,
                maxlength: 32,
                app_username_must : true,
                app_username_exists: true
            },
            /** EMAIL ADDRESS REQUIRED **/
            rau_email_address: {
                required: true,
                email: true,
                email_address_used: true
            },
            /** PASSWORD REQUIRED, BETWEEN 8 - 32 CHARACTER **/
            rau_password: {
                required: true,
                minlength: 8,
                maxlength: 32,
                password_must: true
            },
            /** CONFIRM PASSWORD REQUIRED, MATCH PASSWORD **/
            rau_confirm_password: {
                required: true,
                equalTo: "#rau_password"
            },
            /** VALIDATE FIRST NAME AND LAST NAME **/
            rau_first_name: {
                required: true,
                person_name: true
            },
            rau_last_name: {
                required: true,
                person_name: true
            },
            /** VALIDATE GENDER **/
            rau_gender: {
                required: true
            },
            /** VALIDATE DATE OF BIRTH **/
            rau_date_of_birth: {
                required: true,
                date: true
            },
            /** VALIDATE PHONE NUMBER **/
            rau_phone_number: {
                required: true,
                vietnam_phone_number: true
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE USERNAME **/
            rau_app_username: {
                required: "Username is required!",
                minlength: "Username must be more than 8 character!",
                maxlength: "Username must be less than 32 character!",
                app_username_must: "Username accept only letter or letter and number!",
                app_username_exists: "Username exists, please try again!"
            },
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            rau_email_address: {
                required: "Email is required!",
                email: "Please enter a valid email address!",
                email_address_used: "Email address used for other account, please try again!"
            },
            /** MESSAGE VALIDATE PASSWORD **/
            rau_password: {
                required: "Password is required!",
                minlength: "Password must be more than 8 character!",
                maxlength: "Password must be less than 32 character!",
                password_must: "Passwords must contain including uppercase, lowercase letters and numbers"
            },
            /** MESSAGE VALIDATE CONFIRM PASSWORD **/
            rau_confirm_password: {
                required: "Confirm password is required!",
                equalTo: "Password and Password confirm must be same!"
            },
            /** MESSAGE VALIDATE FIRST NAME **/
            rau_first_name: {
                required: "First name is required!",
                person_name: "Please enter a valid person first name!"
            },
            /** MESSAGE VALIDATE FIRST NAME **/
            rau_last_name: {
                required: "Last name is required!",
                person_name: "Please enter a valid person last name!"
            },
            /** MESSAGE VALIDATE GENDER **/
            rau_gender: {
                required: "Gender is required!"
            },
            /** MESSAGE VALIDATE DATE OF BIRTH **/
            rau_date_of_birth: {
                required: "Date of birth is required!",
                date: "Please enter a valid date of birth!"
            },
            /** MESSAGE VALIDATE PHONE NUMBER **/
            rau_phone_number: {
                required: "Phone number is required!",
                vietnam_phone_number: "Please enter a valid Vietnamese phone number!"
            }
        },
        /**
         *  SUBMIT FORM
         *  AJAX CALL CreateAppUser CONTROLLER
         *  REQUEST BODY AppUser(AppUserDetail)
         */
        submitHandler: function (form) {
            /** LOADING SPINNER **/
            loading();
            /**
             *  GET FORM VALUE
             */
            var appUsername = $("#rau_app_username").val();
            var emailAddress = $("#rau_email_address").val();
            var password = $("#rau_password").val();
            var firstName = $("#rau_first_name").val();
            var lastName = $("#rau_last_name").val();
            var gender = $("input[name='rau_gender']:checked").val();
            var dateOfBirth = $("#rau_date_of_birth").val();
            var phoneNumber = $("#rau_phone_number").val();
            var address = $("#rau_address").val();
            /** APP USER **/
            var appUser = new Object();
            appUser.appUsername = appUsername;
            appUser.encryptedPassword = password;
            /** APP USER DETAIL **/
            var appUserProfile = new Object();
            appUserProfile.emailAddress = emailAddress;
            appUserProfile.firstName = firstName;
            appUserProfile.lastName = lastName;
            appUserProfile.gender = gender;
            appUserProfile.dateOfBirth = dateOfBirth;
            appUserProfile.phoneNumber = phoneNumber;
            appUserProfile.address = address;

            appUser.appUserProfile = appUserProfile;

            /** APP USER ROLE **/
            var appUserRoleSet = new Array();
            var appUserRole = new Object();
            var appRole = new Object();
            appRole.appRoleName = "ROLE_GUEST";
            appUserRole.appRole = appRole;
            appUserRoleSet.push(appUserRole);

            appUser.appUserRoleSet = appUserRoleSet;
            /**
             *  AJAX CALL CreateAppUser CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_create = $.ajax({
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                url: "/CreateAppUser",
                data: JSON.stringify(appUser)
            });
            /** AJAX CALL SUCCESS **/
            ajax_create.done(function (data) {
                /** LOADING SPINNER **/
                unloading();
                data? alertSuccess() : alertError();
            });
        }

    });

    /** ALERT MESSAGE SUCCESS ACTION **/
    function alertSuccess() {
        swal({
            title: "Action Completed!",
            text: "Register app user success, please check your email address confirm registration!",
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
                $("#register_app_user_modal").modal("hide");
                /** RESET FORM RESEND EMAIL CONFIRMATION TOKEN VALUE **/
                resetForm();
                /** OPEN MODAL SIGN-IN ACCOUNT **/
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