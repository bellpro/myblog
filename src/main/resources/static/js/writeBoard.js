function isValidContents(title, contents ) {
    if (title == '' |  contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    return true;
}

function SendPost() {
    let title = $('#boardTitle').val();
    let contents = $('#contents').val();
    if (isValidContents(title, contents) == false) {
        return;
    }
    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'username': "", 'title': title, 'content': contents};

    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/board/write",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('게시글이 저장되었습니다.');
            window.location.href = '/';
        }
    });
}