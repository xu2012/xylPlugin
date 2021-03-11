class LivenessResult {
  LivenessResult(
      {this.filePath,
//        this.base64Url,
        this.result,
        this.livenessId,
        this.transactionId,
        this.livenessScore});

  factory LivenessResult.fromMap(Map<dynamic, dynamic> map) =>
      new LivenessResult(
//        base64Url: map['base64Url'],
        filePath: map["filePath"],
        livenessId: map['livenessId'],
        transactionId: map['transactionId'],
        livenessScore: map['livenessScore'],
        result: map['result']
      );

//  final String base64Url;
  final String livenessId;
  final String transactionId;
  final double livenessScore;
  final String filePath;
  final bool result;
  @override
  String toString() => '$result:$livenessId: $transactionId:$livenessScore:$filePath';
}