$(document).ready(function () {

    var appUser;

    /** INNIT FORM DATA -> LOAD APP USER SIGN INNED AND SET FORM VALUE **/
    function innit_form_value() {
        var ajax_read = $.ajax({
            type: "GET",
            contentType: "application/json",
            dataType: "json",
            url: "/GetSignInnedAppUser",
           async: false
        });
        ajax_read.done(function (data) {
            appUser = data;
            /** SET FORM VALUE **/
            $("#aup_app_username").text(data.appUsername);
            $("#aup_email_address").text(data.appUserProfile.emailAddress);
            $("#aup_first_name").text(data.appUserProfile.firstName);
            $("#aup_last_name").text(data.appUserProfile.lastName);
            $("#aup_date_of_birth").text(data.appUserProfile.dateOfBirth);
            $("#aup_phone_number").text(data.appUserProfile.phoneNumber);
            $("#aup_address").text(data.appUserProfile.address);
            // if(data.appUserProfile.gender) {
            //     $("#aup_male").prop("checked", true);
            // }
            // else {
            //     $("#aup_female").prop("checked", true);
            // }
            $("#app_user_profile_modal").modal("show");
        });
    }

    /** CLICK SHOW PROFILE -> LOAD DATA SIGN INNED USER **/
    $("#app_user_profile_link").click(function () {
        innit_form_value();
    });

    /** DISABLED ALL FIELD ACCEPT **/
    function disabled_accept_edit() {
        $("#aup_email_address").addClass("disabled");
        $("#aup_first_name").addClass("disabled");
        $("#aup_last_name").addClass("disabled");
        $("#aup_date_of_birth").addClass("disabled");
        $("#aup_address").addClass("disabled");
    }

    /** ENABLED ALL FIELD ACCEPT **/
    function enabled_accept_edit() {
        $("#aup_email_address").removeClass("disabled");
        $("#aup_first_name").removeClass("disabled");
        $("#aup_last_name").removeClass("disabled");
        $("#aup_date_of_birth").removeClass("disabled");
        $("#aup_address").removeClass("disabled");
    }

    /** SHOW BUTTON SUBMIT **/
    function show_submit() {
        /** SHOW BUTTON SAVE **/
        $("#aup_submit").removeClass("hide");
        $("#aup_submit").addClass("show");
        /** SHOW BUTTON CANCEL **/
        $("#aup_cancel").removeClass("hide");
        $("#aup_cancel").addClass("show");
        /** HIDE BUTTON EDIT **/
        $("#aup_edit").removeClass("show");
        $("#aup_edit").addClass("hide");
    }

    /** HIDE BUTTON SUBMIT **/
    function hide_submit() {
        /** SHOW BUTTON SAVE **/
        $("#aup_submit").removeClass("show");
        $("#aup_submit").addClass("hide");
        /** SHOW BUTTON CANCEL **/
        $("#aup_cancel").removeClass("show");
        $("#aup_cancel").addClass("hide");
        /** HIDE BUTTON EDIT **/
        $("#aup_edit").removeClass("hide");
        $("#aup_edit").addClass("show");
    }

    /** CLICK BUTTON EDIT **/
    $("#aup_edit").click(function () {
        /** ENABLED ALL FIELD ACCEPT EDIT **/
        enabled_accept_edit();
        /** SHOW SUBMIT **/
        show_submit();
    });

    /** CLICK BUTTON CANCEL **/
    $("#aup_cancel").click(function () {
        /** RESET FORM VALUE **/
        innit_form_value();
        /** HIDE SUBMIT **/
        hide_submit();
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
        $("#aup_spinner").removeClass("fa fa-save");
        $("#aup_spinner").addClass("fa fa-spinner fa-spin");
        $("#aup_submit").addClass("disabled");
        $("#aup_cancel").addClass("disabled");
        $("#aup_reset").addClass("disabled");
    }

    /** UNLOADING **/
    function unloading(flag) {
        /** LOADING SPINNER **/
        $("#aup_spinner").removeClass("fa fa-spinner fa-spin");
        if(flag) {
            $("#aup_edit").removeClass("hide");
            $("#aup_edit").addClass("show");

            $("#aup_submit").removeClass("show");
            $("#aup_submit").removeClass("disabled");
            $("#aup_submit").addClass("hide");

            $("#aup_cancel").removeClass("show");
            $("#aup_cancel").removeClass("disabled");
            $("#aup_cancel").addClass("hide");
        }
        else {
            $("#aup_submit").removeClass("disabled");
            $("#aup_cancel").removeClass("disabled");
        }
        $("#aup_reset").removeClass("disabled");
    }

    /** VALIDATE REGISTER FORM **/
    $("#app_user_profile_form").validate({
        /** RULES **/
        rules: {
            /** EMAIL ADDRESS REQUIRED **/
            aup_email_address: {
                required: true,
                email: true,
                email_address_used: true
            },
            /** VALIDATE FIRST NAME AND LAST NAME **/
            aup_first_name: {
                required: true,
                person_name: true
            },
            aup_last_name: {
                required: true,
                person_name: true
            },
            /** VALIDATE GENDER **/
            aup_gender: {
                required: true
            },
            /** VALIDATE DATE OF BIRTH **/
            aup_date_of_birth: {
                required: true,
                date: true
            },
            /** VALIDATE PHONE NUMBER **/
            aup_phone_number: {
                required: true,
                vietnam_phone_number: true
            }
        },
        /** MESSAGE **/
        messages: {
            /** MESSAGE VALIDATE EMAIL ADDRESS **/
            aup_email_address: {
                required: "Email is required!",
                email: "Please enter a valid email address!",
                email_address_used: "Email address used for other account, please try again!"
            },
            /** MESSAGE VALIDATE FIRST NAME **/
            aup_first_name: {
                required: "First name is required!",
                person_name: "Please enter a valid person first name!"
            },
            /** MESSAGE VALIDATE FIRST NAME **/
            aup_last_name: {
                required: "Last name is required!",
                person_name: "Please enter a valid person last name!"
            },
            /** MESSAGE VALIDATE GENDER **/
            aup_gender: {
                required: "Gender is required!"
            },
            /** MESSAGE VALIDATE DATE OF BIRTH **/
            aup_date_of_birth: {
                required: "Date of birth is required!",
                date: "Please enter a valid date of birth!"
            },
            /** MESSAGE VALIDATE PHONE NUMBER **/
            aup_phone_number: {
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
            var emailAddress = $("#aup_email_address").val();
            var firstName = $("#aup_first_name").val();
            var lastName = $("#aup_last_name").val();
            var gender = $("input[name='aup_gender']:checked").val();
            var dateOfBirth = $("#aup_date_of_birth").val();
            var phoneNumber = $("#aup_phone_number").val();
            var address = $("#aup_address").val();

            /** APP USER PROFILE **/
            var appUserProfile = new Object();
            appUserProfile.emailAddress = emailAddress;
            appUserProfile.firstName = firstName;
            appUserProfile.lastName = lastName;
            appUserProfile.gender = gender;
            appUserProfile.dateOfBirth = dateOfBirth;
            appUserProfile.phoneNumber = phoneNumber;
            appUserProfile.address = address;

            appUser.appUserProfile = appUserProfile;
            /**
             *  AJAX CALL CreateAppUser CONTROLLER
             *  REQUEST BODY AppUser(AppUserDetail)
             */
            var ajax_update = $.ajax({
                type: "PUT",
                contentType: "application/json",
                dataType: "json",
                url: "/UpdateAppUser",
                data: JSON.stringify(appUser)
            });
            /** AJAX CALL SUCCESS **/
            ajax_update.done(function (data) {
                /** LOADING SPINNER **/
                unloading(data);
                data? alertSuccess() : alertError();
            });
        }

    });

    /** ALERT MESSAGE SUCCESS ACTION **/
    function alertSuccess() {
        swal({
            title: "Action Completed!",
            text: "Profile edited success!",
            icon: "success",
            button: {
                text: "OK",
                closeModal: true,
                visible: true,
                className: "btn btn-success btn-lg"
            }
        })
        .then(function (isOk) {
            if(isOk) {
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