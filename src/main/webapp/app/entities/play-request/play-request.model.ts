import { BaseEntity } from './../../shared';

export const enum PlayType {
    'BET',
    'WIN',
    'REFOUND',
    'BALANCE'
}

export class PlayRequest implements BaseEntity {
    constructor(
        public id?: number,
        public playtype?: PlayType,
        public token?: string,
        public system?: string,
        public timestamp?: any,
        public ticketid?: string,
        public transactionid?: string,
        public amount?: number,
        public balance?: number,
        public bonusbalance?: number,
        public user?: string,
        public operator?: string,
    ) {
    }
}
