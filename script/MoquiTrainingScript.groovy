
import org.moqui.context.ExecutionContext
import org.moqui.entity.EntityValue

// This script assumes it's running in the context of a Moqui service-call
ExecutionContext ec = context.ec

// Input parameters
def trainingName = context.trainingName
def trainingDate = context.trainingDate
def trainingPrice = context.trainingPrice
def trainingDuration = context.trainingDuration


// Explicitly generate a unique ID
def trainingID = ec.entity.sequencedIdPrimary("MoquiTraining", null, null)

// Create the MoquiTraining entity record
EntityValue trainingRecord = ec.entity.makeValue "moqui.training.MoquiTraining"


trainingRecord.set("trainingId", trainingId) // Explicitly set trainingId
trainingRecord.set("trainingName", trainingName)
trainingRecord.set("trainingDate", trainingDate)

// Set the output parameter
context.trainingID = trainingRecord.get("trainingId")