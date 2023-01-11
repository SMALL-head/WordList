function check() {
    let inputContent = document.getElementById("listName").value;
    let $newList = $("#newList-judge-response");
    let correctPattern = /^[zxcvbnmasdfghjklpoiuytrewqZXCVBNMLKJHGFDSAPOIUYTREWQ][zxcvbnmasdfghjklpoiuytrewqZXCVBNMLKJHGFDSAPOIUYTREWQ1234567890]*$/
    if (inputContent.length > 25) {
        // $newList.html = "长度不能超过25";
        return false;
    }
    if (!correctPattern.test(inputContent)) {
        // $newList.html = "不能有特殊字符且必须以字母开头";
        return false;
    }
    console.log($newList.html());
    return $newList.html() === "名字正确";
}

let a1 = new Vue({
    el: "#showWords",
    data: {
        translate: "查询中...",
        data1: "data1",
    },
    methods: {
        wordRequest: function (e) {
            let p = new Promise((resolve) => {
                let search_res = "";
                let selectDom = e.target
                let word = selectDom.id
                // console.log(selectDom)
                let requestCommand = "/word/searchWord?word=" + word;
                let res = $.get({
                    url: requestCommand,
                    contentType: "text/plain; charset=utf-8",
                    success: function () {
                        search_res = res.responseText;
                        resolve(search_res);
                    }
                })
            }).then((data) => {
                this.translate = data;
            })
        },
    }
})

let a2 = new Vue({
    el: "#searchBox",
    data: {
        words: [],
        displayStatus: false
    },
    methods: {
        searchByPrefix: function (event) {
            let p = new Promise((resolve) => {
                let search_res = [];
                let selectDom = event.target
                let prefix = selectDom.value
                let request = "/word/prefix?prefix=" + prefix;
                let res = $.get({
                    url: request,
                    success: function () {
                        search_res = res;
                        resolve(search_res);
                    }
                })
            }).then(data => {
                this.words = data;
                console.log(data);
            })
        },

        onfocus: function (event) {
            let elementDom = event.target;
            elementDom.placeholder = "";
            this.displayStatus = true;
        },

        onblur: function (event) {
            let elementDom = event.target;
            elementDom.placeholder = 'Search Word';
            this.displayStatus = false;
        }
    }
})
/*new Vue({
    el: "#addWordModal",
    data: {
        responseFromServ: ""
    },
    methods: {
        addWord: function (word) {
            let p = new Promise((resolve => {
                let url = "/list/addWord";
                let resp = $.post({
                    url: url,
                    contentType: "text/plain; charset=utf-8",
                    data: {
                        listname: $("#selectWordList option:selected").text(),
                        word: word
                    },
                    success: function () {
                        resolve(resp.responseText)
                    }
                })
            })).then( (data) => {
                this.responseFromServ = data;
            })
        }
    }
})*/
