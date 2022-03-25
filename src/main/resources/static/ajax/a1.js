function judge() {
    let value = document.getElementById("listName").value;
    let uid = document.getElementById("uidValue").value;
    let $newList = $("#newList-judge-response");
    if (value === "") {
        $newList.addClass("text-danger");
        $newList.html("不能为空");
        return;
    }
    let htmlobj = $.get({
        url: "/list/newList.do?name=" + value + "&uid=" + uid,
        success: function (data) {
            let res = htmlobj.responseText;

            if (res === '名字正确') {
                $newList.removeClass("text-danger");
                $newList.addClass("text-success")
            } else {
                $newList.addClass("text-danger");
            }
            $newList.html(htmlobj.responseText);

            //检验表单
            let inputContent = document.getElementById("listName").value;
            $newList = $("#newList-judge-response");
            let correctPattern = /^[zxcvbnmasdfghjklpoiuytrewqZXCVBNMLKJHGFDSAPOIUYTREWQ][zxcvbnmasdfghjklpoiuytrewqZXCVBNMLKJHGFDSAPOIUYTREWQ1234567890]*$/
            if (inputContent.length > 25) {
                $newList.addClass("text-danger");
                $newList.html("长度不能超过25");
                return false;
            }
            if (!correctPattern.test(inputContent)) {
                $newList.addClass("text-danger");
                $newList.html("不能有特殊字符且必须以字母开头");
                return false;
            }
            return $newList.html === "名字正确";
        }
    });
}

addWord = function (word, uid) {
    let p = new Promise((resolve => {
        let url = "/list/addWord";
        let resp = $.post({
            url: url,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {
                'listname': $("#selectWordList option:selected").text(),
                'word': word,
                'uid': uid
            },
            success: function () {
                resolve(resp.responseText);
            }
        })
    })).then(data => {
        alert(data);
    })
}

