import React from 'react';
import {GuardSpinner} from "react-spinners-kit";

class Donat extends React.Component{
    constructor(props) {
        super(props);
        this.state = {content : this.loading(),lastTransaction : {balance : 'Loading', amount : 'Loading', comment : 'Loading', transID : 'Loading', sender : 'Loading'}};
        this.handleLoad = this.handleLoad.bind(this);
    }

    componentDidMount() {
        window.addEventListener('load', this.handleLoad);
    }

    componentWillUnmount() {
        window.removeEventListener('load', this.handleLoad)
    }
    loading(){
        return <div id={'loader'}>
            <GuardSpinner size={120} frontColor={'#78ffd6'} backColor={'#f8d351'} />
            <div>Загрузка...</div>
        </div>
    }
    content(){
        return <div id={'Main'}>
            <div id={'Container'}>
                <div id={'Statistic'}>
                    <div id={'Label'}>Собрано</div>
                    <div id={'Data'}>
                        <div id={'DataWithOutUAH'}>
                            <div id={'Current'}>{ this.state.lastTransaction.balance }</div>
                            <hr/>
                            <div id={'Million'}>1 000 000</div>
                        </div>
                        <div id={'uah'}>₴</div>
                    </div>
                </div>
                <div id={'LastTransaction'}>
                    <div id={'Label'}>Вклад № <text is='text' id={'numberOfInvest'}>{this.state.lastTransaction.transID}</text></div>
                    <div id={'Data'}>
                        <div id={'Sum'}>{this.state.lastTransaction.amount}₴</div>
                        <div id={'Comment'}>{this.state.lastTransaction.comment}</div>
                        <div id={'Sender'}>от {this.state.lastTransaction.sender}</div>
                    </div>
                </div>
                <div id={'card'}>5375 4115 0060 5287</div>
                <div id={'Info'}>
                    Жертвуя, даже 1 гривну, вы получаете рекламу до следующейго пожертвования. Указывайте текст в коментарие к платежу! Добру быть!
                </div>
                <div id={'Footer'}>
                    made by Vlad Bobko
                </div>
            </div>
        </div>
    }
    handleLoad() {
        let lastTransactionURL = document.location.origin + '/api/getLastTransaction';
        fetch(lastTransactionURL).then(data =>{
            return data.json()
        }).then((json)=>{
            this.setState({lastTransaction : json})
            document.getElementById('MainLoading').id='Main'
            this.setState({content : this.content()})
            this.myAnimateValue('Current',0,this.state.lastTransaction.balance,1100,true)
            this.myAnimateValue('numberOfInvest',0,this.state.lastTransaction.transID,1100,false)
        })
    }
    myAnimateValue(id,start,end,time, floor){
        let stepTime = 30;
        let step = end / (time / stepTime);
        const obj = document.getElementById(id);
        const timer = setInterval(function () {
            start += step;
            if (floor)
                obj.innerHTML = start.toFixed(2).replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
            else
                obj.innerHTML = start.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
            if (start >= end) {
                obj.innerHTML = String(end).replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
                clearInterval(timer);
            }
        }, stepTime);
    }
    render() {
        return <div id={'MainLoading'}>{this.state.content}</div>;
    }
}
export default Donat