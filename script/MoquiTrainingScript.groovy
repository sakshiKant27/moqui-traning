
import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityValue

// This script assumes it's running in the context of a Moqui service-call
ExecutionContext ec = context.ec

// Input parameters
def trainingName = context.trainingName
def trainingDate = context.trainingDate
def trainingPrice = context.trainingPrice
def trainingDuration = context.trainingDuration


// Explicitly generate a unique id
def trainingId = ec.entity.sequencedIdPrimary("MoquiTraining", null, null)


EntityValue trainingRecord = ec.entity.makeValue ("moqui.training.MoquiTraining")

trainingRecord.set("trainingId", trainingId) // Explicitly set trainingId
trainingRecord.set("trainingName", trainingName)
trainingRecord.set("trainingDate", trainingDate)
if(trainingPrice!=null) trainingRecord.set("trainingPrice",trainingPrice)
if(trainingDate!=null) trainingRecord.set("trainingDuration",trainingDuration)
trainingRecord=trainingRecord.create()
context .trainingId = trainingRecord.get("trainingId")//set output parameter