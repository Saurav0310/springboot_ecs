def call() {
    googlechatnotification message: 'Build_check of job', 
	    notifyAborted: true, 
	    notifyBackToNormal: true, 
	    notifyFailure: true, 
	    notifyNotBuilt: true, 
	    notifySuccess: true, 
	    notifyUnstable: true, 
	    sameThreadNotification: true, 
	    suppressInfoLoggers: true, 
	    url: 'https://chat.googleapis.com/v1/spaces/AAAAMtiDwVM/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=KCqrVb7JZqQwG3l3MuJbaVxUoo6HW5_SxlFFfVtyzjs%3D'
}
