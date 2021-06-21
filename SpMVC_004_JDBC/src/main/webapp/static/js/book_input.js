document.querySelector("DOMContentLoaded", () => {
    document.querySelector("form#book_input").addEventListener("click", (e) => {
        let key = e.key;
        let tagName = e.target.tagName;
        let id = e.target.id;

        if (key === "Enter" && tagName === "INPUT") {
            let text = e.target.value;

            if (id === "bk_ccode") {

                fetch(`${rootPath}/comp/list`).then((res) => {
                    return res.text();
                })
                    .then((result) => {
                        let div = document.createElement("div");
                        div.innerHTML = result;

                    });

                alert("출판사 찾기" + text);
            } else if (id === "bk_acode") {
                alert("저자찾기" + text);
            }
        }
    })
})