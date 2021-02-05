//公用变量
const SERVER_BASE_URL = 'http://localhost:8080';

//获取url中的参数
function getSearch(url, key) {
    const arr = url.split("?") //键值串
    if (arr.length > 1) {
        const KVArr = arr[1].split("&") // 键值对数组
        for (var i = 0; i < KVArr.length; i++) {
            let temp = KVArr[i].split("=");
            if (temp[0] === key) {
                let value = temp[1];
                console.log(value);
                return value
            }
        }
    }
}