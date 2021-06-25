var main = {
    init              : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-trainer-save').on('click', function () {
            _this.trainerSave();
        });
        $('#btn-change-permit').on('click', function () {
            _this.changePermit();
        });
        $('#btn-user-update').on('click', function () {
            _this.userUpdate();
        });
        $('#btn-trainer-update').on('click', function () {
            _this.trainerboardUpdate();
        });
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        })
        $(".checkBtn").on('click', function () {
            var checkBtn = $(this);
            var tr = checkBtn.parent().parent();
            var td = tr.children();

            var no = td.eq(0).text();
            console.log(checkBtn);
            $.ajax({
                type       : 'DELETE',
                url        : '/api/v1/admin/user/' + no,
                dataType   : 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('유저가 삭제되었습니다.');
                window.location.href = '/admin/user';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
        $(".checkBtn1").on('click', function () {
            var checkBtn = $(this);
            var tr = checkBtn.parent().parent();
            var td = tr.children();

            var no = td.eq(0).text();
            console.log(checkBtn);
            $.ajax({
                type       : 'DELETE',
                url        : '/api/v1/admin/board/' + no,
                dataType   : 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('게시글이 삭제되었습니다.');
                window.location.href = '/admin/board';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
        $(".checkBtn2").on('click', function () {
            var checkBtn = $(this);
            var tr = checkBtn.parent().parent();
            var td = tr.children();

            var no = td.eq(0).text();
            console.log(checkBtn);
            $.ajax({
                type       : 'DELETE',
                url        : '/api/v1/admin/trainerboard/' + no,
                dataType   : 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('트레이너 게시글이 삭제되었습니다.');
                window.location.href = '/admin/board';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
        $('.photos').on('click', function () {
            var path = $(this).attr('src')
            showImage(path);

            function showImage(fileCallPath) {
                $(".bigPictureWrapper").css("display", "flex").show();
                $(".bigPicture")
                    .html("<img src='" + fileCallPath + "' >")
                    .show({width: '100%', height: '100%'}, 1000);
            }
        });
        $(".bigPictureWrapper").on("click", function (e) {
            $(".bigPicture").show({width: '0%', height: '0%'}, 1000);
            setTimeout(function () {
                $('.bigPictureWrapper').hide();
            }, 10);
        });

    },
    save              : function () {
        var data = {
            title  : $('#title').val(),
            author : $('#author').val(),
            content: $('#summernote').val()
        };

        $.ajax({
            type       : 'POST',
            url        : '/api/v1/board',
            dataType   : 'json',
            contentType: 'application/json; charset=utf-8',
            data       : JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/board';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
        console.log(data.author)
        console.log(data.title)
        console.log(data.content)
    },
    update            : function () {
        var data = {
            title  : $('#title').val(),
            content: $('#summernote').val()
        };
        var id = $('#id').val();

        $.ajax({
            type       : 'PUT',
            url        : '/api/v1/board/' + id,
            dataType   : 'json',
            contentType: 'application/json; charset=utf-8',
            data       : JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/board/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete            : function () {
        var id = $('#id').val();

        $.ajax({
            type       : 'DELETE',
            url        : '/api/v1/board/' + id,
            dataType   : 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/board';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    trainerSave       : function () {
        let address1 = $('select[name=address1]').val();
        let address2 = $('select[name=address2]').val();
        let address = address1 + " " + address2;
        let categoryList = [];
        let categoryString;
        $('input[name="category"]:checked').each(function (i) {
            categoryList.push($(this).val());
        });

        categoryString = categoryList.toString();
        let thumbnailValue = $('#thumbnail').val().split("\\");

        let data = {
            thumbnailName: thumbnailValue[thumbnailValue.length - 1],
            introduce    : $('#introduce').val(),
            program      : $('#program').val(),
            price10      : $('#price10').val(),
            price20      : $('#price20').val(),
            price30      : $('#price30').val(),
            address      : address,
            category     : categoryString
        };
        console.log(data);

        let form = $('#form')[0];
        let formData = new FormData(form);

        formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));
        formData.append('thumbnail', $('#thumbnail'));
        $($("#file")[0].files).each(function (index, file) {
            formData.append('files', file);
            console.log(file);
        });

        $.ajax({
            type       : 'POST',
            url        : '/api/v1/trainerboard',
            processData: false,
            contentType: false,
            data       : formData,
        }).done(function () {
            alert('트레이너 등록 신청이 완료되었습니다.');
            window.location.href = '/trainerboard';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    changePermit      : function () {
        var id = $('#id').val();
        console.log(id)
        $.ajax({
            type       : 'GET',
            url        : '/api/v1/admin/trainerboard/' + id,
            dataType   : 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('게시글이 승인되었습니다.')
            window.location.href = '/admin/trainerboard/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
    userUpdate        : function () {
        let data = {
            nickName: $('#nickName').val()
        };

        $.ajax({
            type       : 'PUT',
            url        : '/api/v1/userUpdate',
            dataType   : 'json',
            contentType: 'application/json; charset=utf-8',
            data       : JSON.stringify(data)
        }).done(function () {
            alert('닉네임이 수정되었습니다.');
            window.location.href = '/mypage/info';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    trainerboardUpdate: function () {

        let id = $('#id').val();

        let address1 = $('select[name=address1]').val();
        let address2 = $('select[name=address2]').val();
        let address = address1 + " " + address2;
        let categoryList = [];
        let categoryString;
        $('input[name="category"]:checked').each(function (i) {
            categoryList.push($(this).val());
        });

        categoryString = categoryList.toString();
        let thumbnailValue = $('#thumbnail').val().split("\\");

        let data = {
            thumbnailName: thumbnailValue[thumbnailValue.length - 1],
            introduce    : $('#introduce').val(),
            program      : $('#program').val(),
            price10      : $('#price10').val(),
            price20      : $('#price20').val(),
            price30      : $('#price30').val(),
            address      : address,
            category     : categoryString
        };

        let form = $('#form')[0];
        let formData = new FormData(form);

        formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));
        formData.append('thumbnail', $('#thumbnail'));
        $($("#file")[0].files).each(function (index, file) {
            formData.append('files', file);
        });

        $.ajax({
            type       : 'PUT',
            url        : '/api/v1/trainerboard/' + id,
            processData: false,
            contentType: false,
            data       : formData,
        }).done(function () {
            alert('트레이너 등록 신청이 완료되었습니다.');
            window.location.href = '/trainerboard';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    commentSave       : function () {
        let id = $('#id').val();
        let data = {
            boardId: id,
            content: $('#comment-content').val()
        };

        $.ajax({
            type       : 'POST',
            url        : '/api/v1/comment',
            dataType   : 'json',
            contentType: 'application/json; charset=utf-8',
            data       : JSON.stringify(data)
        }).done(function () {
            alert('댓글이 등록되었습니다.');
            window.location.href = '/board/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};
main.init();

function searchHealth() {
    location.href = "/trainerboard/search?address1=전체&address2=&category=헬스";
}

function searchYoga() {
    location.href = "/trainerboard/search?address1=전체&address2=&category=요가";
}

function searchPilates() {
    location.href = "/trainerboard/search?address1=전체&address2=&category=필라테스";
}

function searchBulkUp() {
    location.href = "/trainerboard/search?address1=전체&address2=&category=벌크업";
}

function searchDiet() {
    location.href = "/trainerboard/search?address1=전체&address2=&category=다이어트";
}

function searchBodyProfile() {
    location.href = "/trainerboard/search?address1=전체&address2=&category=바디프로필";
}

function resize(obj) {
    obj.style.height = "1px";
    obj.style.height = (12 + obj.scrollHeight) + "px";
}
