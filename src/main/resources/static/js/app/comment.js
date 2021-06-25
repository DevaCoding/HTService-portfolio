let comment = {
    init: function () {
        let id = $('#id').val();
        $('.btn-comment-edit').on('click', function () {
            let commentEdit = $(this)[0];
            commentEdit.style.display = 'none';
            let commentDelete = $(this).next()[0];
            commentDelete.style.display = 'none';
            let commentUpdate = $(this).next().next()[0];
            commentUpdate.style.display = 'inline-block';
            let commentCancel = $(this).next().next().next()[0];
            commentCancel.style.display = 'inline-block';
            let commentTextarea = $(this).prev()[0];
            commentTextarea.readOnly = null;
        });
        $('.btn-comment-cancel').on('click', function () {
            window.location.href = '/board/' + id;
        });
        $('.btn-comment-delete').on('click', function () {
            let deleteBtn = $(this);
            let commentId = deleteBtn.prev().prev().prev().children().eq(0).val();
            $.ajax({
                type       : 'DELETE',
                url        : '/api/v1/comment/' + commentId,
                dataType   : 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('댓글이 삭제되었습니다.');
                window.location.href = '/board/' + id;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
        $('.btn-comment-update').on('click', function () {
            let commentId = $(this).prev().prev().prev().prev().children().eq(0).val();
            console.log(commentId);
            let data = {
                boardId: id,
                content: $(this).prev().prev().prev().val()
            };
            console.log(data.content);
            $.ajax({
                type       : 'PUT',
                url        : '/api/v1/comment/' + commentId,
                dataType   : 'json',
                data       : JSON.stringify(data),
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                alert('댓글이 수정되었습니다.');
                window.location.href = '/board/' + id;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        });
    }
};
comment.init();