//封装求平均值函数
export function avg(array) {
  var len = array.length;
  var sum = 0;
  for (var i = 0; i < len; i++) {
    sum += array[i];
  }
  return sum / len;
}

//封装求最大值函数
export function winner(obj) {
  var max = 0;
  var winner;
  for (let i = 0; i < obj.length; i++) {
    if (obj[i].means > max) {
      max = obj[i].means;
      winner = obj[i].name;
    } else if (obj[i].means == max) {
      //如果当前算法和上一个算法值相等
      winner += " " + obj[i].name;
    }
  }
  return winner;
}

//最终评估
export function final_Winner(
  algNum,
  accMeans,
  f1ScoreMeans,
  precisionMeans,
  recallMeans
) {
  var obj = [];
  for (let i = 0; i < algNum; i++) {
    var data = [];
    data.push(accMeans[i].means);
    data.push(f1ScoreMeans[i].means);
    data.push(precisionMeans[i].means);
    data.push(recallMeans[i].means);
    obj.push({ data: data, name: accMeans[i].name });
  }
  //对于每个算法j，四种指标均值作为最终指标
  for (let j = 0; j < obj.length; j++) {
    obj[j].means = avg(obj[j].data);
  }
  return winner(obj);
}
