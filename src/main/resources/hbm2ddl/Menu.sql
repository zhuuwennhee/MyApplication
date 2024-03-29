----------- MENU LEVEL 1
INSERT INTO DBO.APP_MENU(APP_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE) VALUES('Home', 'Home', '/app/index.html', 'fa fa-home', 1);
INSERT INTO DBO.APP_MENU(APP_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE) VALUES('Envelope', 'Envelope', '/app/envelope.html', 'fa fa-envelope-o', 2);
INSERT INTO DBO.APP_MENU(APP_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE) VALUES('Cloud upload', 'Cloud upload', '/app/cloud-upload.html', 'fa fa-cloud-upload', 3);
INSERT INTO DBO.APP_MENU(APP_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE) VALUES('Video camera', 'Video camera', '/app/video-camera.html', 'fa fa-video-camera', 4);
INSERT INTO DBO.APP_MENU(APP_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE) VALUES('Stick note', 'Stick note', '/app/sticky-note.html', 'fa fa-sticky-note-o', 5);

----------- MENU LEVEL 2
INSERT INTO DBO.APP_SUB_MENU(APP_SUB_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE, APP_MENU_ID_PARENT) VALUES('File Excel', 'File Excel', '/app/file-excel.html', 'fa fa-file-excel-o', 1, (SELECT AM.APP_MENU_ID FROM APP_MENU AM WHERE AM.APP_MENU_NAME = 'Stick note'));
INSERT INTO DBO.APP_SUB_MENU(APP_SUB_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE, APP_MENU_ID_PARENT) VALUES('File Image', 'File Image', '/app/file-image.html', 'fa fa-file-word-o', 2, (SELECT AM.APP_MENU_ID FROM APP_MENU AM WHERE AM.APP_MENU_NAME = 'Stick note'));
INSERT INTO DBO.APP_SUB_MENU(APP_SUB_MENU_NAME, PAGE_TITLE, PAGE_URL, FONT_AWESOME_ICON, SEQUENCE, APP_MENU_ID_PARENT) VALUES('File Word', 'File Word', '/app/file-word.html', 'fa fa-file-image-o', 3, (SELECT AM.APP_MENU_ID FROM APP_MENU AM WHERE AM.APP_MENU_NAME = 'Stick note'));