if (typeof dwr == 'undefined' || dwr.engine == undefined) throw new Error('You must include DWR engine before including this file');

(function() {
if (dwr.engine._getObject("admin") == undefined) {
var p;

p = {};
p._path = '/dwr';




p.getUserCounts = function(callback) {
return dwr.engine._execute(p._path, 'admin', 'getUserCounts', arguments);
};









p.addKeyword = function(p0, p1, p2, p3, p4, callback) {
return dwr.engine._execute(p._path, 'admin', 'addKeyword', arguments);
};










p.addKeywordInterest = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'addKeywordInterest', arguments);
};















p.addNumber = function(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, callback) {
return dwr.engine._execute(p._path, 'admin', 'addNumber', arguments);
};






p.removeNumber = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeNumber', arguments);
};







p.removeKeyword = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeKeyword', arguments);
};








p.removeKeywordInterest = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeKeywordInterest', arguments);
};







p.assignNumber = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'assignNumber', arguments);
};







p.releaseNumber = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'releaseNumber', arguments);
};








p.addSecondaryUser = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'addSecondaryUser', arguments);
};





p.resetPassword = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'resetPassword', arguments);
};





p.generateNewPassword = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'generateNewPassword', arguments);
};





p.resetAutoReload = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'resetAutoReload', arguments);
};





p.getLastUserReport = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'getLastUserReport', arguments);
};







p.getUserReport = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'getUserReport', arguments);
};







p.getAccountNetworkReport = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'getAccountNetworkReport', arguments);
};






p.getAdminAccountNetworkReport = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'getAdminAccountNetworkReport', arguments);
};





p.getGlobalReportRevenueType = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'getGlobalReportRevenueType', arguments);
};





p.getGlobalReportRevenueCategory = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'getGlobalReportRevenueCategory', arguments);
};





p.getGlobalReportRevenueManagedVsGlobal = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'getGlobalReportRevenueManagedVsGlobal', arguments);
};









p.updateKeyword = function(p0, p1, p2, p3, p4, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateKeyword', arguments);
};










p.updateKeywordInterest = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateKeywordInterest', arguments);
};













p.uploadNumbers = function(p0, p1, p2, p3, p4, p5, p6, p7, p8, callback) {
return dwr.engine._execute(p._path, 'admin', 'uploadNumbers', arguments);
};






p.buyNumber = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'buyNumber', arguments);
};







p.resubscribeNumber = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'resubscribeNumber', arguments);
};






p.releaseAllNumbers = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'releaseAllNumbers', arguments);
};






p.cancelNumber = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'cancelNumber', arguments);
};











p.updateNumber = function(p0, p1, p2, p3, p4, p5, p6, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateNumber', arguments);
};










p.topupCreditAccount = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'topupCreditAccount', arguments);
};









p.topupDebitAccount = function(p0, p1, p2, p3, p4, callback) {
return dwr.engine._execute(p._path, 'admin', 'topupDebitAccount', arguments);
};






p.addBulletin = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'addBulletin', arguments);
};






p.updateUserStatus = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateUserStatus', arguments);
};




p.closeBulletin = function(callback) {
return dwr.engine._execute(p._path, 'admin', 'closeBulletin', arguments);
};




p.disableAutoReload = function(callback) {
return dwr.engine._execute(p._path, 'admin', 'disableAutoReload', arguments);
};






p.addPhoneBookUser = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'addPhoneBookUser', arguments);
};







p.uploadPhoneBookRequest = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'uploadPhoneBookRequest', arguments);
};






p.uploadUserPhoneBookRequest = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'uploadUserPhoneBookRequest', arguments);
};





p.processPhoneBookRequest = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'processPhoneBookRequest', arguments);
};







p.addApp = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'addApp', arguments);
};





p.cancelPhoneBookRequest = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'cancelPhoneBookRequest', arguments);
};





p.addToWatchList = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'addToWatchList', arguments);
};





p.removeFromWatchList = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeFromWatchList', arguments);
};





p.revokeApp = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'revokeApp', arguments);
};






p.enableApp = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'enableApp', arguments);
};






p.disableApp = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'disableApp', arguments);
};






p.adminDisableApp = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'adminDisableApp', arguments);
};






p.adminEnableApp = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'adminEnableApp', arguments);
};










p.adminEditApp = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'adminEditApp', arguments);
};










p.editApp = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'editApp', arguments);
};






p.authorizeApp = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'authorizeApp', arguments);
};






p.updateRestrictions = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateRestrictions', arguments);
};





p.cancelSecondaryUser = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'cancelSecondaryUser', arguments);
};





p.clearRegistrationIP = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'clearRegistrationIP', arguments);
};















p.generateInvoice = function(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, callback) {
return dwr.engine._execute(p._path, 'admin', 'generateInvoice', arguments);
};














p.requestInvoice = function(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, callback) {
return dwr.engine._execute(p._path, 'admin', 'requestInvoice', arguments);
};






p.addManagedAccount = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'addManagedAccount', arguments);
};





p.removeManagedAccount = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeManagedAccount', arguments);
};






p.updateAccountCategory = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateAccountCategory', arguments);
};





p.generateTopUpInvoice = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'generateTopUpInvoice', arguments);
};






p.sendPinCode = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'sendPinCode', arguments);
};





p.checkPinCode = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'checkPinCode', arguments);
};








p.adminSubscribeShortcode = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'adminSubscribeShortcode', arguments);
};








p.updateShortcodeMetaParam = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateShortcodeMetaParam', arguments);
};










p.updateShortcodeMetaResponse = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateShortcodeMetaResponse', arguments);
};








p.updateShortcodeMetaCallback = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'updateShortcodeMetaCallback', arguments);
};






p.subscribeShortcodeCampaignAccount = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'subscribeShortcodeCampaignAccount', arguments);
};






p.unsubscribeShortcodeCampaignAccount = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'unsubscribeShortcodeCampaignAccount', arguments);
};






p.removeShortcodeCampaignAccount = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeShortcodeCampaignAccount', arguments);
};









p.addShortcodeCampaignAccount = function(p0, p1, p2, p3, p4, callback) {
return dwr.engine._execute(p._path, 'admin', 'addShortcodeCampaignAccount', arguments);
};







p.addShortcodeCampaign = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'addShortcodeCampaign', arguments);
};











p.userAddShortcodeCampaignAccount = function(p0, p1, p2, p3, p4, p5, p6, callback) {
return dwr.engine._execute(p._path, 'admin', 'userAddShortcodeCampaignAccount', arguments);
};






p.addPermittedDestination = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'addPermittedDestination', arguments);
};





p.unsubscribeUserShortcode = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'unsubscribeUserShortcode', arguments);
};





p.removeUserShortcode = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeUserShortcode', arguments);
};







p.unsubscribeUserShortcodeKeyword = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'unsubscribeUserShortcodeKeyword', arguments);
};







p.removeUserShortcodeKeyword = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeUserShortcodeKeyword', arguments);
};






p.removeShortcodeCampaign = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeShortcodeCampaign', arguments);
};






p.subscribeShortcodeCampaign = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'subscribeShortcodeCampaign', arguments);
};






p.unsubscribeShortcodeCampaign = function(p0, p1, callback) {
return dwr.engine._execute(p._path, 'admin', 'unsubscribeShortcodeCampaign', arguments);
};








p.addSecondaryAccount = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'addSecondaryAccount', arguments);
};













p.addNumbers = function(p0, p1, p2, p3, p4, p5, p6, p7, p8, callback) {
return dwr.engine._execute(p._path, 'admin', 'addNumbers', arguments);
};








p.addShortcodeMetaParam = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'addShortcodeMetaParam', arguments);
};










p.addShortcodeMetaResponse = function(p0, p1, p2, p3, p4, p5, callback) {
return dwr.engine._execute(p._path, 'admin', 'addShortcodeMetaResponse', arguments);
};








p.addShortcodeMetaCallback = function(p0, p1, p2, p3, callback) {
return dwr.engine._execute(p._path, 'admin', 'addShortcodeMetaCallback', arguments);
};







p.removeShortcodeMetaParam = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeShortcodeMetaParam', arguments);
};







p.removeShortcodeMetaResponse = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeShortcodeMetaResponse', arguments);
};







p.removeShortcodeMetaCallback = function(p0, p1, p2, callback) {
return dwr.engine._execute(p._path, 'admin', 'removeShortcodeMetaCallback', arguments);
};





p.cleanUp = function(p0, callback) {
return dwr.engine._execute(p._path, 'admin', 'cleanUp', arguments);
};

dwr.engine._setObject("admin", p);
}
})();

