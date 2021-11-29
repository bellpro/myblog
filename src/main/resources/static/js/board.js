function detailBoard(id) { // 상세 페이지로 이동
    window.location.href = `/board/detail/${id}`
}

function deleteBoard(id) { //게시글 삭제 기능
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: 'DELETE',
            url: `/board/${id}`,
            success: function (response) {
                alert('삭제되었습니다!');
                window.location.reload();
            }
        })
    }
}

