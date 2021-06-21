import React from 'react';
import VideoFilesService from '../Service/VideoFilesService';
import axios from 'axios';
import ReactPlayer from 'react-player';
<link rel="stylesheet" href="/css/video-react.css" />
interface MyProps {
}

interface MyState {
  links:Array<any>;
  title:string;
  link:string;
  creationDate:string;
  pagenum:any;
}
class VideoFileComponent extends React.Component <MyProps, MyState>{
  constructor(props:any){
    super(props)
    this.state={
      links:[],
      title:'',
      link:'',
      creationDate:'',
      pagenum:1
    }
  }
  componentDidMount(){
    VideoFilesService.getvideoFiles(this.state.pagenum).then((res)=>this.setState({links:res.data}));
  }
  paging(page:any){
    this.setState({pagenum:page})
    this.componentDidMount();
  }
  submit(event:any,title:string){
    event.preventDefault();
    axios.post('http://localhost:8080/videoFiles/save',{
      title:this.state.title,
      link:this.state.link,
      creationDate:this.state.creationDate
    })
    .then((res)=>{
      this.componentDidMount();
    })
    

  }
  delete(title:string){
    VideoFilesService.deleteVideoFiles(title)
    .then(()=>{
      this.componentDidMount();
    })
  }
  edit(title:string){
    VideoFilesService.getVideoFilesbyid(title)
    .then((res)=>{
      console.log(res.data);
      this.setState({
        title:res.data.title,
        link:res.data.link,
        creationDate:res.data.creationDate
      })
    })}
  render(){
    return (
      
       <div className="container" >
    
       <div className="row">
       <div className="col s6">
           <form onSubmit={(e)=>this.submit(e,this.state.title)}>
           <div className="input-field col s12">
             <i className="material-icons prefix">person</i>
             <input onChange={(e)=>this.setState({title:e.target.value})} value={this.state.title} type="text" id="autocomplete-input" className="autocomplete" />
             
           </div>
           <div className="input-field col s12">
             <i className="material-icons prefix">link</i>
             <input onChange={(e)=>this.setState({link:e.target.value})} value={this.state.link} type="text" id="autocomplete-input" className="autocomplete" />
             
           </div>
           <div className="input-field col s12">
             <i className="material-icons prefix">date_range</i>
             <input onChange={(e)=>this.setState({creationDate:e.target.value})} value={this.state.creationDate} type="text" id="autocomplete-input" className="autocomplete" />
             
           </div>
           <button className="btn waves-effect waves-light right" type="submit" name="action">Submit
             <i className="material-icons right">send</i>
           </button>
           </form>
         </div>
         <div className="col s6">
       <ReactPlayer  height='240' controls url={this.state.link}/> 
      </div>
         <div className="col s6">
         <table>
           <thead>
             <tr>
                 <th>Title</th>
                 <th>Link</th>
                 <th>creationDate</th>
             </tr>
           </thead>
   
           <tbody>
             {
               this.state.links.map(link=>
                 <tr key={link.title}>
                   <td>{link.title}</td>
                   <td>{link.link}</td>
                   <td>{link.creationDate}</td>
                   <td>
                   <button onClick={(e)=>this.edit(link.title)} className="btn waves-effect waves-light" type="submit" name="action">
                     <i className="material-icons">edit</i>
                   </button>
                   </td>
                   <td>
                   <button onClick={(e)=>this.delete(link.title)} className="btn waves-effect waves-light" type="submit" name="action">
                     <i className="material-icons">delete</i>
                   </button>
                   </td>
                 </tr>
                 )
             }
             
           </tbody>
         </table>
         <ul className="pagination">
    <li className="disabled"><a href="#!"><i className="material-icons">chevron_left</i></a></li>
    <li className="waves-effect"><button className="active" onClick={(e)=>this.paging(1)}>1</button></li>
    <li className="waves-effect"><a href="#!"><i className="material-icons">chevron_right</i></a></li>
  </ul>
         </div>
       
       </div>
       </div>
  );}
  
}

export default VideoFileComponent;