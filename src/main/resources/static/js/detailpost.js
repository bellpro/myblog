function deleteBoard(id) { //게시글 삭제 기능
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: 'DELETE',
            url: `/board/${id}`,
            success: function (response) {
                alert('삭제되었습니다!');
                window.location.href = '/';
            }
        })
    } else {}
}

function SendUserment(id) { // 댓글 저장 기능
    let userment = $('#userment').val();
    let comment = {'boardId': id, 'writer': "", 'content': userment}

    if (userment !== "") {
        $.ajax({
            type: 'POST',
            url: "/board/detail/comment",
            contentType: "application/json",
            data: JSON.stringify(comment),
            success: function (response) {
                alert(response);
                if (response === "회원이 아닙니다.") {
                    alert("로그인이 필요한 서비스입니다!");
                    window.location.href = '/user/login';
                }else{
                    window.location.reload();
                }
            }
        });
    } else {
        $('#userment').focus();
        alert("댓글을 작성해주세요!");
    }
}

function updateComment(id){ //댓글 수정 기능
    let userment = $(`#${id}-newcmt`).val();
    let comment = {'boardId': id, 'writer': "", 'content': userment}

    if (userment !== "") {
        $.ajax({
            type: 'PUT',
            url: `/comment/${id}`,
            contentType: "application/json",
            data: JSON.stringify(comment),
            success: function (response) {
                if (response === "회원이 아닙니다.") {
                    alert("로그인이 필요한 서비스입니다!");
                    window.location.href = '/user/login';
                }else{
                    window.location.reload();
                }
            }
        });
    } else {
        $(`#${id}-newcmt`).focus();
        alert("댓글을 작성해주세요!");
    }
}

function deleteComment(id) { // 댓글 삭제 기능
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: 'DELETE',
            url: `/comment/${id}`,
            success: function (response) {
                window.location.reload();
            }
        })
    } else {
    }
}



function showEdits(id){
    $(`#${id}-editarea`).show();
    $(`#${id}-update`).show();

    $(`#${id}-cmt`).hide();
    $(`#${id}-edit`).hide();
}

function EditComment(id){
    showEdits(id);
    let originMent = $(`#${id}-cmt`).text().trim();
    $(`#${id}-newcmt`).val(originMent);
}

