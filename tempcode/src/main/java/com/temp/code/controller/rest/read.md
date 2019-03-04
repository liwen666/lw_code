spring  的rest 如果对象中有属性是byte
数组 

需要使用Base64Utils.decodeFromString 获取到传过来的byte数组   这样可以对数据进行统一的byte编码转换
 private BpmnTemplateDef transitionObjToTemp(Object b)  {
        BpmnTemplateDef temp = new BpmnTemplateDef();
        LinkedHashMap<String ,Object> data = (LinkedHashMap<String, Object>) b;
        Field[] declaredFields = temp.getClass().getDeclaredFields();
        for (String key : data.keySet()) {
            for (Field ftemp : declaredFields) {
                ftemp.setAccessible(true);
                if (ftemp.getName().equals(key)) {
                    try {

                        if(key.equals("contentBytes")){
                          System.out.println(new String (data.get(key).toString().getBytes(),"gbk"));
                            ftemp.set(temp, Base64Utils.decodeFromString((String) data.get(key)));
                            continue;
                        }
                        ftemp.set(temp, data.get(key));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return temp;
    }