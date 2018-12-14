BEGIN
DELETE FROM Bd_t_Initparambean WHERE beanid = 'basSystemYearService' AND appid = 'BAS';
INSERT INTO Bd_t_Initparambean(datakey, beanid, BEANNAME, APPID, TYPE, status)
VALUES
('506ADAA9FEAF650BE0533A06A8C0A7F1', 'basSystemYearService', '下发年度', 'BAS', 'busiYear', '1');
END;
--下发年度Handler登记
