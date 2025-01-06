import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityValue

// This script assumes it's running in the context of a Moqui service-call
ExecutionContext ec = context.ec

// Input parameters
def trainingName = context.trainingName
def trainingDate = context.trainingDate
def trainingPrice = context.trainingPrice
def trainingDuration = context.trainingDuration

def trainingRecord = ec.entity.find("moqui.training.MoquiTraining")
if (context.trainingId) {
    trainingRecord.condition("trainingId", context.trainingId)
} else if (context.trainingName) {
    trainingRecord.condition("trainingName", context.trainingName)
}
trainingRecord = trainingRecord.one()

if (trainingRecord) {
    // Update existing record
    trainingRecord.set("trainingDate", context.trainingDate)
    trainingRecord.set("trainingPrice", context.trainingPrice)
    trainingRecord.set("trainingDuration", context.trainingDuration)
    trainingRecord.set("trainingName", context.trainingName)
    trainingRecord.update()
    context.trainingId = trainingRecord.trainingId
} else {
    // Create a new record
    def newTrainingId = ec.entity.sequencedIdPrimary("moqui.training.MoquiTraining", null, null)
    ec.entity.makeValue("moqui.training.MoquiTraining")
            .set("trainingId", newTrainingId)
            .set("trainingName", context.trainingName)
            .set("trainingDate", context.trainingDate)
            .set("trainingPrice", context.trainingPrice)
            .set("trainingDuration", context.trainingDuration)
            .create()
    context.trainingId = newTrainingId
}

