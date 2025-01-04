def createMoquiTraining(Map input, ExecutionContext ec) {
    def trainingName = input.trainingName
    def trainingDate = ec.l10n.formatDate(input.trainingDate, "MM/dd/yyyy")


    if (!trainingName) {
        ec.message.addError("trainingName is mandatory")
        return [:]
    }
    if (!trainingDate) {
        ec.message.addError("trainingDate must follow MM/dd/yyyy format")
        return [:]
    }

    def result = ec.service.sync()
            .name("create#MoquiTraining")
            .parameters([trainingName: trainingName, trainingDate: trainingDate])
            .call()

    return [
            trainingId: result.trainingId
    ]
}
