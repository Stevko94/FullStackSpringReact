import axios from 'axios';

const API_URL="http://localhost:8080/videoFiles/";

class VideoFilesService{
    getvideoFiles(pagenum:number)
    {
      return axios.get(API_URL+'getAll/'+pagenum);
    }
    getVideoFilesbyid(title:string)
    {
      return axios.get(API_URL+'getById/'+title);
    }
    saveFiles()
    {
      return axios.post(API_URL+'save/');
    }
    deleteVideoFiles(title:string)
    {
       return axios.delete(API_URL+'deleteById/'+title);
    }
}
export default new VideoFilesService()